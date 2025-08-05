package com.zzarit.oreum.member.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);

    @Query("""
      SELECT CASE
               WHEN m.category IS NOT NULL THEN m.category.type
               ELSE NULL
             END
      FROM Member m
      WHERE m.id = :memberId
    """)
    Type findTypeByMember(@Param("memberId") Long memberId);
}