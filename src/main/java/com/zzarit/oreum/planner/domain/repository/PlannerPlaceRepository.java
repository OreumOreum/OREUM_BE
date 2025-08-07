package com.zzarit.oreum.planner.domain.repository;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.planner.domain.PlannerPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlannerPlaceRepository extends JpaRepository<PlannerPlace, Long> {
    List<PlannerPlace> findAllByPlanner(Planner planner);

    void deleteAllByPlanner(Planner planner);

    @Query("""
            SELECT MAX (pp.sequenceDay)
            FROM PlannerPlace pp
            where pp.planner.id = :id
""")
    Optional<Integer> findMaxDayByMember(Long id);
}