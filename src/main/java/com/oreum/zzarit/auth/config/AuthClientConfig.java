package com.oreum.zzarit.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class AuthClientConfig {


    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

}
