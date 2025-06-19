package com.oreum.zzarit.member.domain.repository;

public enum AuthProvider {
    KAKAO, GOOGLE, APPLE;

    public String buildLoginId(String id){
        return this.name() + id;
    }
}
