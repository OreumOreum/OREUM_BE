package com.zzarit.oreum.planner.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    void deleteAllByMember(Member member);

    List<Planner> findAllByMember(Member member);
    Optional<Planner> findByIdAndMember(Long id, Member member);

    @Query("SELECT p.name FROM Planner p WHERE p.member.id = :memberId AND p.name LIKE :pattern")
    List<String> findNamesByNameStartingWith(@Param("pattern") String pattern, @Param("memberId") Long memberId);
}