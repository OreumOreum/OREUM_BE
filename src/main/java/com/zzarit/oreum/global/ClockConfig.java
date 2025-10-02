package com.zzarit.oreum.global;

import java.time.Clock;
import java.time.ZoneId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 시스템 시계 설정 클래스
 * 
 * 애플리케이션에서 사용할 시스템 시계를 한국 시간대(Asia/Seoul)로 설정합니다.
 * JWT 토큰의 만료 시간 계산 및 시간 관련 로직에서 사용됩니다.
 */
@Configuration
public class ClockConfig {

    @Bean
    Clock clock() {
        return Clock.system(ZoneId.of("Asia/Seoul"));
    }
}
