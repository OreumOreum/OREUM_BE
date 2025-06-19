package com.oreum.zzarit.auth.config;

import com.oreum.zzarit.global.exception.InternalServerException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

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
