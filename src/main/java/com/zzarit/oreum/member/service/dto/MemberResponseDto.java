package com.zzarit.oreum.member.service.dto;

import com.zzarit.oreum.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String loginId;
    private final String name;
    private final String email;
    private final String phoneNo;
    private final String categoryType;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phoneNo = member.getPhoneNo();
        this.categoryType = member.getCategory().getType().name(); // Category 객체에서 타입 이름을 가져옵니다.
    }
}