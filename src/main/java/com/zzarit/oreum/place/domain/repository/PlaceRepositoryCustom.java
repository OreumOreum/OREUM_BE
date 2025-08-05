package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.service.dto.PlaceSearchConditionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaceRepositoryCustom {
    Page<Place> searchPlaces(PlaceSearchConditionDto condition, Pageable pageable, Integer sigunguCode);

    Page<Place> findPlaceList(String contentTypeId, Integer sigunguCode, Type type, Pageable pageable);

}