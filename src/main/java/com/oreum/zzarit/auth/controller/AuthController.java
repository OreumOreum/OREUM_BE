package com.oreum.zzarit.auth.controller;


import com.oreum.zzarit.auth.service.AuthService;
import com.oreum.zzarit.auth.service.dto.AuthTokenDto;
import com.oreum.zzarit.auth.service.dto.KakaoLoginRequestDto;
import com.oreum.zzarit.auth.service.dto.RefreshTokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/refresh")
    public ResponseEntity<AuthTokenDto> refresh(
            @RequestBody RefreshTokenRequestDto request) {
        AuthTokenDto tokens = authService.refresh(request.refreshToken());
        return ResponseEntity.ok(tokens);
    }


}
