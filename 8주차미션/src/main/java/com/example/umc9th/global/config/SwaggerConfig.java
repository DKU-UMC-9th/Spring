package com.example.umc9th.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        // 기본 정보
        Info info = new Info()
                .title("Project API")
                .description("Project Swagger Documentation")
                .version("0.0.1");

        // JWT 인증 스키마 이름
        String schemeName = "JWT";

        // JWT 인증 요구
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(schemeName);

        // JWT 스키마 설정
        Components components = new Components()
                .addSecuritySchemes(schemeName,
                        new SecurityScheme()
                                .name(schemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );

        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
