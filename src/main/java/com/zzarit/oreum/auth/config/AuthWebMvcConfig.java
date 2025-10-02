package com.zzarit.oreum.auth.config;

import com.zzarit.oreum.auth.service.AuthTokenManager;
import com.zzarit.oreum.auth.service.JwtTokenProvider;
import com.zzarit.oreum.auth.service.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 인증 및 인가 Web MVC 설정 클래스
 * 
 * Spring MVC에서 인증 및 인가 처리를 위한 설정을 제공합니다.
 * Member 객체를 자동으로 주입하는 ArgumentResolver와
 * JWT 토큰 검증을 위한 Interceptor를 등록합니다.
 */
@RequiredArgsConstructor
@Configuration
public class AuthWebMvcConfig implements WebMvcConfigurer {

    private final AuthTokenManager authTokenManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenParser tokenParser;

    /**
     * 커스텀 Argument Resolver를 등록합니다.
     * 
     * MemberArgumentResolver를 등록하여 컨트롤러 메서드에서
     * Member 타입의 파라미터를 자동으로 주입받을 수 있도록 설정합니다.
     * 
     * @param resolvers Argument Resolver 목록
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberArgumentResolver(authTokenManager, tokenParser));
    }

    /**
     * 인터셉터를 등록합니다.
     * 
     * AuthorizationInterceptor를 등록하여 JWT 토큰 검증을 수행합니다.
     * 인증이 필요없는 경로(로그인, Swagger 문서, 헬스체크 등)는
     * 예외 처리하여 인터셉터를 거치지 않도록 설정합니다.
     * 
     * @param registry 인터셉터 레지스트리
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(authTokenManager,tokenParser))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/v1/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html/**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs/swagger-config",
                        "/static/swagger-ui/openapi3.yaml",
                        "/health-check",
                        "/read-only/**",
                        "/favicon.ico"
                );
    }
}
