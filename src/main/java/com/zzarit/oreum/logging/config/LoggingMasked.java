package com.zzarit.oreum.logging.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로깅 마스킹 어노테이션
 * 
 * 컴트롤러 메소드에 이 어노테이션을 사용하면 요청 본문을 로깅에서 마스킹합니다.
 * 민감한 정보(로그인 토큰, 비밀번호 등)를 다루는 API에서 사용합니다.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggingMasked {
}
