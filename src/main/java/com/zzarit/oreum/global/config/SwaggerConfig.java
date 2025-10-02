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

/**
 * Swagger API 문서 설정 클래스
 * 
 * SpringDoc OpenAPI를 사용하여 API 문서를 자동 생성하고 설정합니다.
 * JWT 인증이 필요한 API에 대한 보안 스키마를 설정하며,
 * Member 타입 파라미터를 Swagger 문서에서 숨기는 커스터마이저를 제공합니다.
 */
@Configuration
public class SwaggerConfig {
    /**
     * OpenAPI 설정 빈을 생성합니다.
     * 
     * API 문서의 기본 정보와 JWT 인증을 위한 보안 스키마를 설정합니다.
     * Bearer Token 방식의 JWT 인증을 전역적으로 적용합니다.
     * 
     * @return OpenAPI 설정 객체
     */
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


    /**
     * Member 타입 파라미터를 Swagger 문서에서 숨기는 커스터마이저 빈을 생성합니다.
     * 
     * 컨트롤러 메서드에서 Member 타입의 파라미터는 AuthArgumentResolver를 통해
     * 자동으로 주입되므로, API 문서에서는 표시하지 않도록 필터링합니다.
     * 
     * @return OperationCustomizer Member 파라미터를 숨기는 커스터마이저
     */
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
