package com.oreum.zzarit.auth.service;

import com.oreum.zzarit.auth.service.client.KakaoAuthClient;
import com.oreum.zzarit.auth.service.dto.AuthTokenDto;
import com.oreum.zzarit.auth.service.dto.KakaoLoginRequestDto;
import com.oreum.zzarit.global.exception.UnauthorizedException;
import com.oreum.zzarit.member.domain.Member;
import com.oreum.zzarit.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final KakaoAuthClient kakaoAuthClient;
    private final AuthTokenManager authTokenManager;
    private final MemberRepository memberRepository;

    public AuthTokenDto kakaoLogin(KakaoLoginRequestDto request){
        String loginId = kakaoAuthClient.getUserInfo(request.accessToken());
        Member member = memberRepository.findByLoginId(loginId).orElseGet(() -> signUp(loginId));
        return login(member);
    }

    private Member signUp(String loginId) {
        Member member = memberRepository.save(Member.builder()
                .loginId(loginId)
                .build());

        return member;
    }

    private AuthTokenDto login(Member member) {
        AuthTokenDto authToken = authTokenManager.createToken(member);
        return authToken;
    }

    public AuthTokenDto refresh(String refreshToken) {
        if (!authTokenManager.isValid(refreshToken)) {
            throw new UnauthorizedException("이미 사용한 토큰입니다, 다시 로그인 해주세요.");
        }

        return authTokenManager.refresh(refreshToken);
    }

}
