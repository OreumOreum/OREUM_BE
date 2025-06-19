package com.zzarit.oreum.auth.service;

import com.zzarit.oreum.auth.service.client.OAuthClient;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuthClientComposite {
    private final Map<AuthProvider, OAuthClient> clientMap = new HashMap<>();
    private final OAuthClient devClient;

    private final boolean isDev;

    public OAuthClientComposite(List<OAuthClient> clients) {
        this.isDev = isDevProfile(); // 현재 프로필 확인

        if (isDev) {
            this.devClient = clients.get(0); // dev 프로필이면 유일한 DevOAuthClient만 있음
        } else {
            this.devClient = null;
            for (OAuthClient client : clients) {
                clientMap.put(client.getProvider(), client);
            }
        }
    }

    public OAuthClient getClient(AuthProvider provider) {
        return isDev ? devClient : clientMap.get(provider);
    }

    private boolean isDevProfile() {
        String[] profiles = System.getProperty("spring.profiles.active", "dev").split(",");
        return Arrays.asList(profiles).contains("dev");
    }

}
