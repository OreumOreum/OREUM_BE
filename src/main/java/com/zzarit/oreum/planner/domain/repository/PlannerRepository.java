package com.zzarit.oreum.planner.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    void deleteAllByMember(Member member);

    List<Planner> findAllByMember(Member member);
    Optional<Planner> findByIdAndMember(Long id, Member member);
}