package com.zzarit.oreum.auth.config;

import com.zzarit.oreum.auth.service.AuthTokenManager;
import com.zzarit.oreum.auth.service.TokenParser;
import com.zzarit.oreum.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT 토큰 기반 인증 처리 인터셉터
 * 
 * HTTP 요청의 Authorization 헤더에서 JWT 토큰을 추출하고 검증하여
 * 인증된 사용자 정보를 요청 속성에 저장합니다.
 */
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final AuthTokenManager authTokenManager;
    private final TokenParser tokenParser;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = tokenParser.getAccessToken(request.getHeader("Authorization"));
        Member member = authTokenManager.findMemberByAccessToken(token);
        request.setAttribute("authenticatedMember", member);
        return true;
    }
}
