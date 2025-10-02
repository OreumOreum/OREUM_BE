package com.zzarit.oreum.logging.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 로깅 인터셉터
 * 
 * 컴트롤러 메소드에 @LoggingMasked 어노테이션이 있는지 확인하여
 * 로깅 마스킹 여부를 설정하는 역할을 합니다.
 * 민감한 정보(로그인 토큰 등)가 로깅에서 마스킹되도록 합니다.
 */
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws IOException {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return;
        }
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(LoggingMasked.class)) {
            request.setAttribute("loggingMasked", true);
        }
    }
}
