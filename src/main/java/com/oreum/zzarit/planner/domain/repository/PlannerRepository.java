package com.oreum.zzarit.planner.domain.repository;

import com.oreum.zzarit.planner.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
}