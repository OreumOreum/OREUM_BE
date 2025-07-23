package com.zzarit.oreum.place.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.zzarit.oreum.member.domain.QCategory;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.QPlace;
import com.zzarit.oreum.place.domain.QPlaceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    List<Place> findBySigunguCode(Integer sigunguCode);
    List<Place> findBySigunguCodeAndIdNotIn(Integer sigunguCode, List<Long> ids);
    Page<Place> findPlaceList(Integer sigunguCode, Type type, Pageable pageable);

    @Query("select p.contentId from Place p where p.contentId in :ids")
    List<String> findAllContentIdIn(@Param("ids") Collection<String> ids);
}