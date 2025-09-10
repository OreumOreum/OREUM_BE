package com.zzarit.oreum.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

/**
 * OAuth 클라이언트 설정 클래스
 * 
 * 외부 OAuth 서비스와의 통신을 위한 RestClient Bean을 설정합니다.
 */
@Configuration
public class AuthClientConfig {


    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

}
