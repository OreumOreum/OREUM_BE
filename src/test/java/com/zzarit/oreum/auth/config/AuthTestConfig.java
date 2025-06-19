package com.zzarit.oreum.auth.config;

import com.zzarit.oreum.auth.service.client.KakaoAuthClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class AuthTestConfig {

    @Bean
    public KakaoAuthClient kakaoAuthClient() {
        return mock(KakaoAuthClient.class); // Mockito mock
    }
}
