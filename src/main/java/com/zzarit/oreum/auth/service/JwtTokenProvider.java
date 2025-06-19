package com.zzarit.oreum.auth.service;

import java.time.Clock;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import com.zzarit.oreum.auth.service.dto.AuthTokenDto;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String accessSecretKey;
    private final String refreshSecretKey;
    private final Duration accessTokenExpired;
    private final Duration refreshTokenExpired;
    private final Clock clock;

    public JwtTokenProvider(@Value("${security.jwt.token.access-secret-key}") String accessSecretKey,
                            @Value("${security.jwt.token.refresh-secret-key}") String refreshSecretKey,
                            @Value("${security.jwt.token.access-token-expired}") Duration accessTokenExpired,
                            @Value("${security.jwt.token.refresh-token-expired}") Duration refreshTokenExpired,
                            Clock clock) {
        this.accessSecretKey = Base64.getEncoder().encodeToString(accessSecretKey.getBytes());
        this.refreshSecretKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
        this.accessTokenExpired = accessTokenExpired;
        this.refreshTokenExpired = refreshTokenExpired;
        this.clock = clock;
    }

    public AuthTokenDto createAuthToken(String payload, String sessionId) {
        return new AuthTokenDto(createAccessToken(payload, accessSecretKey, accessTokenExpired),
                createRefreshToken(payload, refreshSecretKey, refreshTokenExpired, sessionId));
    }

    private String createAccessToken(String payload, String secretKey, Duration expired) {
        return Jwts.builder()
                .setSubject(payload)
                .setExpiration(calculateExpiredAt(expired))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String createRefreshToken(String payload, String secretKey, Duration expired, String sessionId) {
        return Jwts.builder()
                .setSubject(payload)
                .setId(sessionId)
                .setExpiration(calculateExpiredAt(expired))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Date calculateExpiredAt(Duration expired) {
        Date now = Date.from(clock.instant());
        return new Date(now.getTime() + expired.toMillis());
    }

    public Long getMemberIdByAccessToken(String token) {
        String memberId = getClaimsByToken(token, accessSecretKey).getSubject();
        return Long.valueOf(memberId);
    }


    public Long getMemberIdByRefreshToken(String token) {
        String memberId = getClaimsByToken(token, refreshSecretKey).getSubject();
        return Long.valueOf(memberId);
    }

    public String getSessionIdByRefreshToken(String token) {
        return getClaimsByToken(token, refreshSecretKey).getId();
    }


    private Claims getClaimsByToken(String token, String secretKey) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .setClock(() -> Date.from(clock.instant()))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("토큰이 만료되었습니다.");
        } catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException("유효하지 않은 토큰입니다.");
        }
    }

}
