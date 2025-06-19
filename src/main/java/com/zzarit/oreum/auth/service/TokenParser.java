package com.zzarit.oreum.auth.service;

import com.zzarit.oreum.global.exception.UnauthorizedException;
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
