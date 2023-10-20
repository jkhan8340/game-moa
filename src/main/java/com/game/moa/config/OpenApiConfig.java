package com.game.moa.config;

import com.game.moa.auth.MemberInfo;
import com.game.moa.auth.TokenProvider;
import com.game.moa.controller.LoginRestController;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI config() {

        SpringDocUtils.getConfig().addAnnotationsToIgnore(MemberInfo.class);

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(TokenProvider.AUTHORIZATION_HEADER))
                .components(new Components()
                        .addSecuritySchemes(TokenProvider.AUTHORIZATION_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name(TokenProvider.AUTHORIZATION_HEADER)))
                .info(new Info().title("Game-moa API")
                        .description("Game Moa application")
                        .version("v1.0.0")
                        .license(new License().name("JK HAN Co., Ltd").url("")));
    }
}
