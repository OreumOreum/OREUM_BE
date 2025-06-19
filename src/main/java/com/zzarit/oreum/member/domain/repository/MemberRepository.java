package com.zzarit.oreum.member.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // findAllById(), findAll(), save(), delete() 등등 이미 만들어져 있음
    // 기능 X -> 무조건 쿼리 데이터베이스 접근
    // 스프링 생태계 -> spring data JPA

    Optional<Member> findByLoginId(String loginId);


    @Query("SELECT m FROM Member m WHERE m.loginId = :loginId")
    List<Member> example1(@Param("loginId") Long loginId);

//    public List<Member> findByLoginId2(String loginId) {
//
//        return queryFactory.selectFrom(user)
//                .where(builder)
//                .fetch();
//    }


}