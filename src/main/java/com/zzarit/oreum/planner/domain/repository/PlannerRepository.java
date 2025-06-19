package com.zzarit.oreum.planner.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    void deleteAllByMember(Member member);

    List<Planner> findAllByMember(Member member);
}