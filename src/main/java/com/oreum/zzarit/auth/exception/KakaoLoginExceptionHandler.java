package com.oreum.zzarit.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreum.zzarit.auth.service.dto.KakaoLoginFailResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

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
