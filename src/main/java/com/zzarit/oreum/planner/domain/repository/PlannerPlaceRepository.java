package com.zzarit.oreum.planner.domain.repository;

import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.planner.domain.PlannerPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannerPlaceRepository extends JpaRepository<PlannerPlace, Long> {
    List<PlannerPlace> findAllByPlanner(Planner planner);

    void deleteAllByPlanner(Planner planner);
}