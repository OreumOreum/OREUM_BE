package com.zzarit.oreum.planner.domain.repository;

import com.zzarit.oreum.planner.domain.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
}