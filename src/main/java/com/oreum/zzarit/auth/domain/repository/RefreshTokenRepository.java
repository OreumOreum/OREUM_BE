package com.oreum.zzarit.auth.domain.repository;

import com.oreum.zzarit.auth.domain.RefreshToken;
import com.oreum.zzarit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMemberAndSessionId(Member member, String sessionId);
    boolean existsByMemberAndSessionId(Member member, String sessionId);
}