package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;

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
