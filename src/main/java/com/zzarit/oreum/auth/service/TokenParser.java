package com.zzarit.oreum.auth.service;

import com.zzarit.oreum.global.exception.UnauthorizedException;
import org.springframework.stereotype.Component;


/**
 * HTTP 헤더에서 JWT 토큰을 추출하는 파서
 * 
 * Authorization 헤더에서 Bearer 형식의 토큰을 추출하고
 * 유효성을 검증합니다.
 */
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
