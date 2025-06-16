package com.oreum.zzarit.auth.service;

import java.util.UUID;

import com.oreum.zzarit.auth.domain.RefreshToken;
import com.oreum.zzarit.auth.domain.repository.RefreshTokenRepository;
import com.oreum.zzarit.auth.service.dto.AuthTokenDto;
import com.oreum.zzarit.global.exception.NotFoundException;
import com.oreum.zzarit.global.exception.UnauthorizedException;
import com.oreum.zzarit.member.domain.Member;
import com.oreum.zzarit.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

