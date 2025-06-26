package com.zzarit.oreum.member.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // JSON 바인딩을 위해 기본 생성자가 필요합니다.
public class UpdateMemberCategoryRequest {
    private String categoryType;
}