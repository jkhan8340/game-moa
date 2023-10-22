package com.game.moa.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(
        basePackages = "com.game.moa.repository.redis",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASPECTJ, pattern = "com.game.moa.repository.jpa.*"
        )
)
public class RedisConfig {

        @Bean
        public RedisConnectionFactory redisConnectionFactory(@Value("${spring.data.redis.host}") String host, @Value("${spring.data.redis.port}") int port) {
                return new LettuceConnectionFactory(host, port);
        }

}
