package com.zzarit.oreum.place.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.service.CourseService;
import com.zzarit.oreum.place.service.PlaceService;
import com.zzarit.oreum.place.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PLACE", description = "장소 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @Operation(summary = "여행지 페이지네이션 조회", description = "유형필터시 type=true, 리뷰좋은순 sort=review,DESC")
    @GetMapping()
    public ResponseEntity<PlacesResponseDto> getPlacePagination(
            @RequestParam(required = false) String contentTypeId,
            @RequestParam(required = false) Integer sigunguCode,
            @RequestParam(name = "type", defaultValue = "false") boolean typeFilter,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable,
            Member member
            ){
        return ResponseEntity.ok(placeService.getPlacePagination(contentTypeId,sigunguCode,pageable,member,typeFilter));
    }


    @Operation(summary = "장소 검색 API", description = "DB에 등록된 장소를 검색합니다.")
    @GetMapping("/search-places")
    public ResponseEntity<PlaceSearchResponseDto> searchPlaces(
            @RequestParam String keyword,
            @RequestParam(required = false) Integer sigunguCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PlaceSearchConditionDto condition = new PlaceSearchConditionDto(keyword);
        Page<Place> places = placeService.getSearchPlaces(condition, sigunguCode,page,size);

        List<PlaceDto> content = places.getContent()
                .stream()
                .map(PlaceDto::from)
                .toList();

        PlaceSearchResponseDto response = new PlaceSearchResponseDto(content, places.isLast());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "단일 여행지 상세보기", description = "여행지 상세정보 조회합니다.")
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceDetailResponseDto> searchPlaces(
            @PathVariable long placeId,
            Member member
    ) {
        return ResponseEntity.ok(placeService.getPlaceDetail(placeId,member));
    }

    @Operation(summary = "유형/관광타입별 추천 리스트", description = "모든 관광타입의 대표사진하나씩을 조회합니다.")
    @GetMapping("/category/recommend")
    public ResponseEntity<List<CategoryRecommendResponseDto>> recommendPlacesList(
            Member member
    ) {
        return ResponseEntity.ok(placeService.getCategoryRecommend(member));
    }


    @Operation(summary = "내가 쓴 리뷰 조회", description = "내가 쓴 리뷰를 모두 조회합니다.")
    @GetMapping("/review/me")
    public ResponseEntity<List<MyReviewResponseDto>> getMyReviews(
            Member member){
        return ResponseEntity.ok(placeService.getMyReviews(member));
    }

    @Operation(summary = "단일 여행지 리뷰 페이지네이션 조회", description = "여행지에 대한 리뷰를 페이지네이션합니다.")
    @GetMapping("/review/{placeId}")
    public ResponseEntity<ReviewPaginationResponseDto> getReviewPagination(
            @PathVariable long placeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(placeService.getReviewPaginationByPlace(placeId,page,size));
    }





    @Operation(summary = "단일 여행지 별점,리뷰 생성 API", description = "단일 여행지 별점/리뷰를 생성합니다.")
    @PostMapping("/review")
    public ResponseEntity<Void> createReview(Member member,@RequestBody ReviewCreateRequestDto request){
        placeService.createReviewAndRating(member, request);
        return ResponseEntity.noContent().build();
    }


}