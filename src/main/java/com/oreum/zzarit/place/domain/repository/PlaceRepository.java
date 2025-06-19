package com.oreum.zzarit.place.domain.repository;

import com.oreum.zzarit.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}