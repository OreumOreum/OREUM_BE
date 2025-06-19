package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.member.domain.repository.AuthProvider;
import org.springframework.stereotype.Component;

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
