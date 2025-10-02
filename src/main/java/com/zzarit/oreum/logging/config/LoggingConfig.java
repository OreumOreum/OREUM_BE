package com.zzarit.oreum.logging.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 로깅 설정 클래스
 * 
 * HTTP 요청/응답 로깅을 위한 LoggingInterceptor를 등록합니다.
 * 메소드 레벨에서 로깅 마스킹 여부를 결정하는 역할을 합니다.
 */
@AllArgsConstructor
@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}
