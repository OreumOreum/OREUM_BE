package com.zzarit.oreum.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzarit.oreum.auth.service.dto.KakaoLoginFailResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * 카카오 로그인 에러 처리 핸들러
 * 
 * 카카오 API 호출 시 발생하는 HTTP 에러 응답을 처리하고
 * 적절한 예외로 변환하는 핸들러입니다.
 */
@Slf4j
public class KakaoLoginExceptionHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new KaKaoLoginFailException(getKakaoLoginErrorMessage(response));
    }

    private String getKakaoLoginErrorMessage(final ClientHttpResponse response) throws IOException {
        KakaoLoginFailResponseDto kakaoLoginFailResponse = objectMapper.readValue(
                response.getBody(), KakaoLoginFailResponseDto.class);
        log.error(kakaoLoginFailResponse.toString());
        return kakaoLoginFailResponse.toString();
    }
}
