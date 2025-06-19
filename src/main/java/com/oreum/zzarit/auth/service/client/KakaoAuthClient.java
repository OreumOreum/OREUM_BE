package com.oreum.zzarit.auth.service.client;

import com.oreum.zzarit.auth.exception.KaKaoLoginFailException;
import com.oreum.zzarit.auth.exception.KakaoLoginExceptionHandler;
import com.oreum.zzarit.auth.service.dto.KakaoLoginResponseDto;
import com.oreum.zzarit.member.domain.repository.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KakaoAuthClient {

    private static final String BEARER_HEADER_FORMAT = "Bearer %s";
    private static final String GET_KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    private final RestClient restClient;

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

    private String createAuthorization(String accessToken) {
        return BEARER_HEADER_FORMAT.formatted(accessToken);
    }
}
