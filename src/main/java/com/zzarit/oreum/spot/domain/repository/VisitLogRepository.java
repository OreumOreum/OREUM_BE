package com.zzarit.oreum.spot.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface VisitLogRepository extends JpaRepository<VisitLog, Long> {
    boolean existsByMemberAndSpot(Member member, Spot spot);
    @Query("select v.member from VisitLog v where v.spot.id = :spotId")
    List<Member> findMembersBySpotId(@Param("spotId") Long spotId);

    @Query("SELECT DISTINCT vl.spot.id FROM VisitLog vl WHERE vl.member = :member")
    Set<Long> findVisitedSpotIdsByMember(@Param("member") Member member);
}