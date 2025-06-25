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

        GoogleIdToken googleIdToken = verifyGoogleIdToken(idToken,verifier);

        if (googleIdToken == null) {
            throw new UnauthorizedException("ID 토큰이 유효하지 않습니다.");
        }

        GoogleIdToken.Payload payload = googleIdToken.getPayload();

        return AuthProvider.GOOGLE.buildLoginId(payload.getSubject());
    }

    private GoogleIdToken verifyGoogleIdToken(String idToken, GoogleIdTokenVerifier verifier) {
        try {
            return verifier.verify(idToken);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.GOOGLE;
    }
}
