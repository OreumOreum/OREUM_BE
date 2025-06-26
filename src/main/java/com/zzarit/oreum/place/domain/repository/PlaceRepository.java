package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    List<Place> findBySigunguCodeAndIdNotIn(Integer sigunguCode, List<Long> ids);
}