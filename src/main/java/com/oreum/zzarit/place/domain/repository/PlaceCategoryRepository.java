package com.oreum.zzarit.place.domain.repository;

import com.oreum.zzarit.place.domain.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Long> {
}