package com.zzarit.oreum.spot.domain.repository;

import com.zzarit.oreum.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}