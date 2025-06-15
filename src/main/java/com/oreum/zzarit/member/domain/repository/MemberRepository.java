package com.oreum.zzarit.member.domain.repository;

import com.oreum.zzarit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}