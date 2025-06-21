package com.zzarit.oreum.auth.service.client;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.zzarit.oreum.global.exception.InternalServerException;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RequiredArgsConstructor
@Component
public class GoogleAuthClient implements OAuthClient{

    @Value("${GOOGLE_CLIENT_ID}")
    private String GOOGLE_CLIENT_ID;

    @Override
    public String getUserInfo(String idToken) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();

        GoogleIdToken googleIdToken = null;
        try {
            googleIdToken = verifier.verify(idToken);
            if (googleIdToken == null) {
                throw new UnauthorizedException("#1 ID토큰이 유효하지 않습니다.");
            }
        } catch (GeneralSecurityException e) {
            throw new UnauthorizedException("#2 ID토큰이 유효하지 않습니다.");
        } catch (IOException e) {
            throw new InternalServerException("잠시 뒤 다시 시도해 주세요.");
        }

        GoogleIdToken.Payload payload = googleIdToken.getPayload();

        return AuthProvider.GOOGLE.buildLoginId(payload.getSubject());
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.GOOGLE;
    }
}
