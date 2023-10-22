package com.game.moa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.game.moa.repository",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASPECTJ, pattern = "com.game.moa.repository.redis.*"
        )
)
public class JpaConfig {
}
