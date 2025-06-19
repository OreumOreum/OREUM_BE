package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}