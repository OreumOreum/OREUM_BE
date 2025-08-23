package com.zzarit.oreum.member.domain.repository;

import java.util.Locale;

public enum AuthProvider {
    KAKAO, GOOGLE, APPLE;

    public String buildLoginId(String id){
        return this.name() + id;
    }
}
