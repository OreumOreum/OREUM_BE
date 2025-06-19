package com.zzarit.oreum.auth.domain.repository;

import com.zzarit.oreum.auth.domain.RefreshToken;
import com.zzarit.oreum.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMemberAndSessionId(Member member, String sessionId);
    boolean existsByMemberAndSessionId(Member member, String sessionId);


}