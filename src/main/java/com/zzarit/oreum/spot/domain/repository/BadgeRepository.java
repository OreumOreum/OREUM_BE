package com.zzarit.oreum.spot.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.spot.domain.Badge;
import com.zzarit.oreum.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    boolean existsByMemberAndSpot(Member member, Spot spot);
}