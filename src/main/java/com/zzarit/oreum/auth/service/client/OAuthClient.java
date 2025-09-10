package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.member.domain.repository.AuthProvider;

/**
 * OAuth 클라이언트 인터페이스
 * 
 * 다양한 OAuth 제공업체(Kakao, Google, Apple)와의
 * 인증 처리를 위한 공통 인터페이스입니다.
 */
public interface OAuthClient {
    String getUserInfo(String accessToken);
    AuthProvider getProvider();
}
