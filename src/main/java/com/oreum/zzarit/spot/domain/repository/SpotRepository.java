package com.oreum.zzarit.spot.domain.repository;

import com.oreum.zzarit.spot.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}