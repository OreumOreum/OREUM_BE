package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.auth.exception.KakaoLoginExceptionHandler;
import com.zzarit.oreum.auth.service.dto.KakaoLoginResponseDto;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Component
public class KakaoAuthClient implements OAuthClient{

    private static final String BEARER_HEADER_FORMAT = "Bearer %s";
    private static final String GET_KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    private final RestClient restClient;


    @Override
    public String getUserInfo(String accessToken) {
        KakaoLoginResponseDto responseDto = restClient
                .get()
                .uri(GET_KAKAO_USER_INFO_URI)
                .header(HttpHeaders.AUTHORIZATION, createAuthorization(accessToken))
                .retrieve()
                .onStatus(new KakaoLoginExceptionHandler())
                .body(KakaoLoginResponseDto.class);

        return AuthProvider.KAKAO.buildLoginId(responseDto.id().toString());
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.KAKAO;
    }

    private String createAuthorization(String accessToken) {
        return BEARER_HEADER_FORMAT.formatted(accessToken);
    }

}
