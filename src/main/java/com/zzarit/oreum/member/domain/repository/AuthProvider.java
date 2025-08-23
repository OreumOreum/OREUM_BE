package com.zzarit.oreum.member.domain.repository;

import java.util.Locale;

public enum AuthProvider {
    KAKAO, GOOGLE, APPLE;

    public String buildLoginId(String id){
        return this.name() + id;
    }

    public static AuthProvider fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("AuthProvider 값이 비어있습니다.");
        }
        try {
            return AuthProvider.valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("지원하지 않는 AuthProvider: " + value);
        }
    }
}
