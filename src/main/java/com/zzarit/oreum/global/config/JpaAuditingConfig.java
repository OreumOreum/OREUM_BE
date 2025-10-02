package com.zzarit.oreum.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 감사 기능 설정 클래스
 * 
 * JPA Entity의 생성일시, 수정일시 등을 자동으로 관리하는
 * Auditing 기능을 활성화합니다.
 */
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfig {
}
