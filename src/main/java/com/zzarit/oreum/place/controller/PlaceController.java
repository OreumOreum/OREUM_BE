package com.zzarit.oreum.place.controller;

import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.service.PlaceService;
import com.zzarit.oreum.place.service.dto.PlaceDto;
import com.zzarit.oreum.place.service.dto.PlaceSearchConditionDto;
import com.zzarit.oreum.place.service.dto.PlaceSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/search-places")
    public ResponseEntity<PlaceSearchResponseDto> searchPlaces(
            @RequestParam String keyword,
            Pageable pageable
    ) {
        PlaceSearchConditionDto condition = new PlaceSearchConditionDto(keyword);
        Page<Place> places = placeService.getSearchPlaces(condition, pageable);

        List<PlaceDto> content = places.getContent()
                .stream()
                .map(PlaceDto::from)
                .toList();

        PlaceSearchResponseDto response = new PlaceSearchResponseDto(content, places.isLast());
        return ResponseEntity.ok(response);
    }
}