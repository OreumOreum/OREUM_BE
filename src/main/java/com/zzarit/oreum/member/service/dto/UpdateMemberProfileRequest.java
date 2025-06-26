package com.zzarit.oreum.member.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMemberProfileRequest {
    private String name;
    private String email;
    private String phoneNo;

}