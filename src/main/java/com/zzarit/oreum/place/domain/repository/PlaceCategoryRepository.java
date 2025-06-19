package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCategoryRepository extends JpaRepository<PlaceCategory, Long> {
}