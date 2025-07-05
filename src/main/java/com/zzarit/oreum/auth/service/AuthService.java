package com.zzarit.oreum.auth.service;

import com.zzarit.oreum.auth.service.client.OAuthClient;
import com.zzarit.oreum.auth.service.dto.AppleLoginRequestDto;
import com.zzarit.oreum.auth.service.dto.AuthTokenDto;
import com.zzarit.oreum.auth.service.dto.GoogleLoginRequestDto;
import com.zzarit.oreum.auth.service.dto.KakaoLoginRequestDto;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final OAuthClientComposite oAuthClientComposite;
    private final AuthTokenManager authTokenManager;
    private final MemberRepository memberRepository;


    public AuthTokenDto kakaoLogin(KakaoLoginRequestDto request){
        OAuthClient kakaoAuthClient = oAuthClientComposite.getClient(AuthProvider.KAKAO);
        String loginId = kakaoAuthClient.getUserInfo(request.accessToken());
        Member member = memberRepository.findByLoginId(loginId).orElseGet(() -> signUp(loginId));
        return login(member);
    }

    public AuthTokenDto googleLogin(GoogleLoginRequestDto request){
        OAuthClient googleAuthClient = oAuthClientComposite.getClient(AuthProvider.GOOGLE);
        String loginId = googleAuthClient.getUserInfo(request.idToken());
        Member member = memberRepository.findByLoginId(loginId).orElseGet(() -> signUp(loginId));
        return login(member);
    }

    public AuthTokenDto appleLogin(AppleLoginRequestDto request){
        OAuthClient googleAuthClient = oAuthClientComposite.getClient(AuthProvider.APPLE);
        String loginId = googleAuthClient.getUserInfo(request.authorizationCode());
        Member member = memberRepository.findByLoginId(loginId).orElseGet(() -> signUp(loginId));
        return login(member);
    }

    private Member signUp(String loginId) {
        Member member = memberRepository.save(
                new Member(loginId)
        );

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
