package com.zzarit.oreum.auth.controller;


import com.zzarit.oreum.auth.service.AuthService;
import com.zzarit.oreum.auth.service.dto.AuthTokenDto;
import com.zzarit.oreum.auth.service.dto.GoogleLoginRequestDto;
import com.zzarit.oreum.auth.service.dto.KakaoLoginRequestDto;
import com.zzarit.oreum.auth.service.dto.RefreshTokenRequestDto;
import com.zzarit.oreum.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<Void> test(Member member,
            @RequestBody KakaoLoginRequestDto request) {
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/login/kakao")
    public ResponseEntity<AuthTokenDto> kakaoLogin(
            @RequestBody KakaoLoginRequestDto request) {
        AuthTokenDto tokens = authService.kakaoLogin(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/login/google")
    public ResponseEntity<AuthTokenDto> googleLogin(
            @RequestBody GoogleLoginRequestDto request) {
        AuthTokenDto tokens = authService.googleLogin(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthTokenDto> refresh(
            @RequestBody RefreshTokenRequestDto request) {
        AuthTokenDto tokens = authService.refresh(request.refreshToken());
        return ResponseEntity.ok(tokens);
    }


}
