package com.zzarit.oreum.auth.controller;


import com.zzarit.oreum.auth.service.AuthService;
import com.zzarit.oreum.auth.service.dto.*;
import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AUTH", description = "인증 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

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

    @PostMapping("/login/apple")
    public ResponseEntity<AuthTokenDto> appleLogin(
            @RequestBody AppleLoginRequestDto request) {
        AuthTokenDto tokens = authService.appleLogin(request);
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthTokenDto> refresh(
            @RequestBody RefreshTokenRequestDto request) {
        AuthTokenDto tokens = authService.refresh(request.refreshToken());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/member/exist")
    public ResponseEntity<Boolean> refresh(
            @RequestBody MemberExistCheckDto request) {
        return ResponseEntity.ok(authService.checkJoin(request));
    }


}