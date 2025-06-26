package com.zzarit.oreum.place.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.service.PlaceService;
import com.zzarit.oreum.place.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PLACE", description = "장소 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @Operation(summary = "장소 검색 API", description = "DB에 등록된 장소를 검색합니다.")
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

    @Operation(summary = "코스 리스트 검색 API", description = "유형에 맞는 코스 리스트를 제공합니다.")
    @GetMapping("/course")
    public ResponseEntity<List<CourseResponseDto>> getCourseList(Member member){
        List<CourseResponseDto> dtos = placeService.getCourseList(member);
        return ResponseEntity.ok(dtos);
    }



    @Operation(summary = "별점,리뷰 생성 API", description = "DB에 별점 리뷰를 생성합니다.")
    @PostMapping("/review")
    public ResponseEntity<Void> createReviewAndRating(Member member,@RequestBody ReviewCreateRequestDto request){
        placeService.createReviewAndRating(member, request);
        return ResponseEntity.noContent().build();
    }
}