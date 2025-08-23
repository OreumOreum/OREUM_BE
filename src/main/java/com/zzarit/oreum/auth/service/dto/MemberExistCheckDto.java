package com.zzarit.oreum.auth.service.dto;

import com.zzarit.oreum.member.domain.repository.AuthProvider;

public record MemberExistCheckDto(
        String token,
        AuthProvider provider
){}
