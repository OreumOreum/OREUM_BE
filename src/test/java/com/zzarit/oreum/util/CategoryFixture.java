package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Type;

/**
 * 테스트용 Category 엔티티 픽처 클래스
 * 
 * 단위 테스트에서 사용하는 Category 객체를 생성하는 유틸리티 메소드를 제공합니다.
 */
public class CategoryFixture {

    public static Category category(Type type) {
        Category category = new Category();
        category.setType(type);
        return category;
    }
}
