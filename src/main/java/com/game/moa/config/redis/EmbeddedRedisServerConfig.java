package com.game.moa.config.redis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import redis.embedded.RedisServer;

import java.io.File;
import java.io.IOException;

@Log4j2
@Configuration
@Profile({"dev", "local", "test"})
public class EmbeddedRedisServerConfig {

    private final int port;

    private RedisServer redisServer;

    private final static File REDIS_SERVER_FOR_ARM;

    private final static boolean IS_ARM =  System.getProperty("os.arch").contains("aarch64");

    static {
        try {
            if (IS_ARM) {
                REDIS_SERVER_FOR_ARM = new ClassPathResource("redis/redis-server-7.2.2-mac-arm64").getFile();
            } else {
                REDIS_SERVER_FOR_ARM = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EmbeddedRedisServerConfig(@Value("${spring.data.redis.port}") int port) {
        this.port = port;
    }

    @PostConstruct
    public void startServer() {
        if (REDIS_SERVER_FOR_ARM != null) {
            redisServer = new RedisServer(REDIS_SERVER_FOR_ARM, port);
        } else {
            redisServer = RedisServer.builder()
                    .port(port)
                    .build();
        }
        redisServer.start();
        log.info("Local Redis Started! port : " + redisServer.ports());
    }

    @PreDestroy
    public void stopServer() {
        redisServer.stop();
        log.info("local Redis stop!");
    }

}
