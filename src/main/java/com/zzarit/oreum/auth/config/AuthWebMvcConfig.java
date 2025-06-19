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

@RequiredArgsConstructor
@Configuration
public class AuthWebMvcConfig implements WebMvcConfigurer {

    private final AuthTokenManager authTokenManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenParser tokenParser;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberArgumentResolver(authTokenManager, tokenParser));
    }

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
