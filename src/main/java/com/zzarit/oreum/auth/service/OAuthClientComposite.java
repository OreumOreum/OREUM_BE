package com.zzarit.oreum.auth.service;

import com.zzarit.oreum.auth.service.client.DevAuthClient;
import com.zzarit.oreum.auth.service.client.OAuthClient;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class OAuthClientComposite {
    private final Map<AuthProvider, OAuthClient> clientMap = new HashMap<>();
    private final OAuthClient devClient;
    private final boolean isLocal;


    public OAuthClientComposite(List<OAuthClient> clients, DevAuthClient devAuthClient, Environment env) {
        isLocal = Arrays.asList(env.getActiveProfiles()).contains("123");
        if(isLocal) log.warn("어플리케이션 실행환경이 DEV 상태입니다, 프로덕션 환경이라면 서버 중지가 필요합니다.");

        this.devClient = devAuthClient;
        for (OAuthClient client : clients) {
            clientMap.put(client.getProvider(), client);
        }

    }

    public OAuthClient getClient(AuthProvider provider) {
        return isLocal ? devClient : clientMap.get(provider);
    }
}
