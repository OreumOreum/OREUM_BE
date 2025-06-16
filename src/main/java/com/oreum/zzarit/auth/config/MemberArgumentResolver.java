package com.oreum.zzarit.auth.config;

import com.oreum.zzarit.auth.service.AuthTokenManager;
import com.oreum.zzarit.auth.service.TokenParser;
import com.oreum.zzarit.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@AllArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthTokenManager authTokenManager;
    private final TokenParser tokenParser;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Member.class);
    }

    @Override
    public Member resolveArgument(MethodParameter parameter,
                                        ModelAndViewContainer mavContainer,
                                        NativeWebRequest webRequest,
                                        WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return (Member) request.getAttribute("authenticatedMember");
    }
}
