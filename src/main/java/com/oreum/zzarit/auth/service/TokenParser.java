package com.oreum.zzarit.auth.service;

import com.oreum.zzarit.global.exception.BadRequestException;
import com.oreum.zzarit.global.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;


@Component
public class TokenParser {

    public String getAccessToken(String token) {
        if(token == null || !token.startsWith("Bearer ")){
            throw new UnauthorizedException("유효하지 않은 토큰 형식입니다.");
        }
        token = token.substring(7);
        return token;
    }


}
