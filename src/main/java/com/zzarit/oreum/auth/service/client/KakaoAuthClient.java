package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.auth.exception.KaKaoLoginFailException;
import com.zzarit.oreum.auth.exception.KakaoLoginExceptionHandler;
import com.zzarit.oreum.auth.service.dto.KakaoLoginResponseDto;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * 카카오 OAuth 인증 클라이언트
 * 
 * 카카오 API를 사용하여 사용자 인증 또는 기본 정보를 가져오는 기능을 제공합니다.
 * 액세스 토큰을 사용하여 카카오 서버와 통신합니다.
 */
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
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new KaKaoLoginFailException(
                            "Kakao API error: " + response.getStatusCode()
                    );
                })
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
