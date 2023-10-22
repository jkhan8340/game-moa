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

    public EmbeddedRedisServerConfig(@Value("${spring.data.redis.port}") int port) {
        this.port = port;
    }

    @PostConstruct
    public void startServer() throws IOException {
        if (isArmArchitecture()) {
            redisServer = new RedisServer(getMacSupportRedis(), port);
        } else {
            redisServer = RedisServer.builder()
                    .port(port)
                    .setting("maxmemory 128M")
                    .build();
        }
        redisServer.start();
        log.info("local Redis Started! port : " + redisServer.ports());
    }

    @PreDestroy
    public void stopServer() {
        redisServer.stop();
        log.info("local Redis stop!");
    }

    private boolean isArmArchitecture() {
        return System.getProperty("os.arch").contains("aarch64");
    }


    private File getMacSupportRedis() throws IOException {
        return new ClassPathResource("redis/redis-server-7.2.2-mac-arm64").getFile();
    }
}
