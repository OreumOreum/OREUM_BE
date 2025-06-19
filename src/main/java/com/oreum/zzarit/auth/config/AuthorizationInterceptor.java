package com.oreum.zzarit.auth.config;

import com.oreum.zzarit.auth.service.AuthTokenManager;
import com.oreum.zzarit.auth.service.JwtTokenProvider;
import com.oreum.zzarit.auth.service.TokenParser;
import com.oreum.zzarit.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

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
