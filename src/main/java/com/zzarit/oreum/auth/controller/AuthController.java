package com.zzarit.oreum.auth.controller;


import com.zzarit.oreum.auth.service.AuthService;
import com.zzarit.oreum.auth.service.dto.*;
import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "카카오 로그인 API", description = "카카오 OAuth를 통해 로그인하고 JWT 토큰을 발급받습니다.")
    @PostMapping("/login/kakao")
    public ResponseEntity<AuthTokenDto> kakaoLogin(
            @RequestBody KakaoLoginRequestDto request) {
        AuthTokenDto tokens = authService.kakaoLogin(request);
        return ResponseEntity.ok(tokens);
    }

    @Operation(summary = "구글 로그인 API", description = "구글 OAuth를 통해 로그인하고 JWT 토큰을 발급받습니다.")
    @PostMapping("/login/google")
    public ResponseEntity<AuthTokenDto> googleLogin(
            @RequestBody GoogleLoginRequestDto request) {
        AuthTokenDto tokens = authService.googleLogin(request);
        return ResponseEntity.ok(tokens);
    }

    @Operation(summary = "애플 로그인 API", description = "애플 OAuth를 통해 로그인하고 JWT 토큰을 발급받습니다.")
    @PostMapping("/login/apple")
    public ResponseEntity<AuthTokenDto> appleLogin(
            @RequestBody AppleLoginRequestDto request) {
        AuthTokenDto tokens = authService.appleLogin(request);
        return ResponseEntity.ok(tokens);
    }

    @Operation(summary = "토큰 갱신 API", description = "리프레시 토큰을 사용하여 새로운 액세스 토큰을 발급받습니다.")
    @PostMapping("/refresh")
    public ResponseEntity<AuthTokenDto> refresh(
            @RequestBody RefreshTokenRequestDto request) {
        AuthTokenDto tokens = authService.refresh(request.refreshToken());
        return ResponseEntity.ok(tokens);
    }

    @Operation(summary = "회원 조회 API", description = "이미 가입된 회원인지 조회합니다.")
    @PostMapping("/member/exist")
    public ResponseEntity<Boolean> refresh(
            @RequestBody MemberExistCheckDto request) {
        return ResponseEntity.ok(authService.checkJoin(request));
    }


}