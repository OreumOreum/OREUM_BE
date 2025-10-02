package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;

/**
 * 테스트용 Member 엔티티 픽처 클래스
 * 
 * 단위 테스트에서 사용하는 Member 객체를 생성하는 유틸리티 메소드를 제공합니다.
 * 다양한 상황에 맞는 Member 객체를 생성할 수 있도록 여러 팩토리 메소드를 지원합니다.
 */
public class MemberFixture {

    public static Member member() {
        return new Member("testLoginId");
    }

    public static Member member(Category category) {
        Member member = new Member("testLoginId");
        member.setCategory(category);
        return member;
    }

    public static Member member(String loginId, Category category) {
        Member member = new Member(loginId);
        member.setCategory(category);
        return member;
    }
}
