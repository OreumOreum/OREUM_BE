package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    List<Place> findBySigunguCodeAndIdNotIn(Integer sigunguCode, List<Long> ids);
    Page<Place> searchPlaceList(String cityCode, Type type, Pageable pageable);
}