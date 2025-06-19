package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.member.domain.repository.AuthProvider;

public interface OAuthClient {
    String getUserInfo(String accessToken);
    AuthProvider getProvider();
}
