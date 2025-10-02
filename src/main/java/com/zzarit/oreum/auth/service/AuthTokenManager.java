package com.zzarit.oreum.auth.service;

import java.util.UUID;

import com.zzarit.oreum.auth.domain.RefreshToken;
import com.zzarit.oreum.auth.domain.repository.RefreshTokenRepository;
import com.zzarit.oreum.auth.service.dto.AuthTokenDto;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * JWT 토큰 및 리프레시 토큰 관리 서비스
 * 
 * 사용자 인증을 위한 JWT 토큰 생성, 검증, 갱신 기능을 제공합니다.
 * 리프레시 토큰은 데이터베이스에 저장되어 관리됩니다.
 */
@RequiredArgsConstructor
@Component
public class AuthTokenManager {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthTokenDto createToken(Member member) {
        String sessionId = UUID.randomUUID().toString();
        AuthTokenDto authToken = jwtTokenProvider.createAuthToken(member.getId().toString(), sessionId);
        RefreshToken auth = new RefreshToken(member, sessionId, authToken.refreshToken());
        refreshTokenRepository.save(auth);
        return authToken;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean isValid(String refreshToken) {
        Member member = findMemberByRefreshToken(refreshToken);
        String sessionId = jwtTokenProvider.getSessionIdByRefreshToken(refreshToken);
        RefreshToken refreshTokenEntity = findRefreshTokenFromRepository(member, sessionId);
        if (!refreshTokenEntity.isValid(refreshToken)) {
            refreshTokenRepository.delete(refreshTokenEntity);
            return false;
        }
        return true;
    }

    private Member findMemberByRefreshToken(String token) {
        Long memberId = jwtTokenProvider.getMemberIdByRefreshToken(token);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("유저"));
    }


    private RefreshToken findRefreshTokenFromRepository(Member member, String sessionId) {
        return refreshTokenRepository.findByMemberAndSessionId(member, sessionId)
                .orElseThrow(() -> new UnauthorizedException("유효하지 않은 토큰입니다."));
    }

    @Transactional
    public AuthTokenDto refresh(String refreshToken) {
        Member member = findMemberByRefreshToken(refreshToken);
        String sessionId = jwtTokenProvider.getSessionIdByRefreshToken(refreshToken);
        RefreshToken refreshTokenEntity = findRefreshTokenFromRepository(member, sessionId);
        AuthTokenDto authTokenDto = jwtTokenProvider.createAuthToken(member.getId().toString(), sessionId);
        refreshTokenEntity.refresh(authTokenDto.refreshToken());
        return authTokenDto;
    }


    public Member findMemberByAccessToken(String token) {
        Long memberId = jwtTokenProvider.getMemberIdByAccessToken(token);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new UnauthorizedException("유저"));
    }
}

