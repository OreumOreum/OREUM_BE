package com.zzarit.oreum.auth.config;

import com.zzarit.oreum.global.exception.InternalServerException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * OAuth 클라이언트 요청 타임아웃 처리 인터셉터
 * 
 * 외부 OAuth 서비스(Kakao, Google, Apple)로의 HTTP 요청 시
 * IOException이 발생할 경우 사용자 친화적인 메시지로 변환합니다.
 */
public class AuthClientTimeoutInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {
        try {
            return execution.execute(request, body);
        } catch (IOException exception) {
            throw new InternalServerException("시간이 초과 되어 로그인 요청 실패하였습니다.");
        }
    }
}
