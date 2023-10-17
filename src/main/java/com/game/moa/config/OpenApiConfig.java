package com.game.moa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI config() {
        return new OpenAPI()
                .info(new Info().title("Game-moa API")
                        .description("Game Moa application")
                        .version("v1.0.0")
                        .license(new License().name("JK HAN Co., Ltd").url("")));
    }
}
