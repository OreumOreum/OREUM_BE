//package com.oreum.zzarit.global.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(
//                        new Components()
//                                .addSecuritySchemes("accessToken", new SecurityScheme()
//                                        .name("accessToken")
//                                        .type(SecurityScheme.Type.APIKEY)
//                                        .in(SecurityScheme.In.HEADER)
//                                        .bearerFormat("JWT")
//                                )
//                );
//    }
//}
