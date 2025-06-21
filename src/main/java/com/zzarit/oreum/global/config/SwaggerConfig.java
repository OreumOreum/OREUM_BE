package com.zzarit.oreum.global.config;


import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.stream.Stream;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "Authorization";

        return new OpenAPI()
                .info(new Info()
                        .title("OREUM API")
                        .version("v1")
                        .description("오름오름 API 문서"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) // 전역 적용
                .components(
                        new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("Bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }


    @Bean
    public OperationCustomizer hideMemberTypeParams() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            if (operation.getParameters() != null && handlerMethod != null) {
                operation.getParameters().removeIf(parameter ->
                        handlerMethod.getMethodParameters() != null &&
                                handlerMethod.getMethodParameters().length > 0 &&
                                Stream.of(handlerMethod.getMethodParameters())
                                        .anyMatch(p -> p.getParameterType().equals(Member.class) &&
                                                p.getParameter().getName().equals(parameter.getName()))
                );
            }
            return operation;
        };
    }
}
