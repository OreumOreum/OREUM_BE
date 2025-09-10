package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.member.domain.repository.AuthProvider;
import org.springframework.stereotype.Component;

/**
 * 개발용 Mock OAuth 클라이언트
 * 
 * 개발 환경에서 실제 OAuth 제공업체와의 통신 없이
 * 테스트를 수행할 수 있도록 하는 Mock 구현체입니다.
 */
@Component
public class DevAuthClient implements OAuthClient{

    @Override
    public String getUserInfo(String accessToken) {
        return accessToken;
    }

    @Override
    public AuthProvider getProvider() {
        return null;
    }
}
