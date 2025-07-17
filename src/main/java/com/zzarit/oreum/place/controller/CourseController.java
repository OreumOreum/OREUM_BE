package com.zzarit.oreum.place.controller;


import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.service.CourseService;
import com.zzarit.oreum.place.service.dto.CourseDetailResponseDto;
import com.zzarit.oreum.place.service.dto.CourseResponseDto;
import com.zzarit.oreum.place.service.dto.CourseReviewCreateRequestDto;
import com.zzarit.oreum.place.service.dto.ReviewPaginationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "COURSE", description = "코스 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "코스 리스트 검색 API", description = "유형에 맞는 코스 리스트를 제공합니다.")
    @GetMapping()
    public ResponseEntity<List<CourseResponseDto>> getCourseList(Member member){
        List<CourseResponseDto> dtos = courseService.getCourseList(member);
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "코스 상세 검색 API", description = "요청한 코스의 상세보기를 제공합니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDetailResponseDto> getCourseList(@PathVariable long courseId){
        return ResponseEntity.ok(courseService.getCourseDetail(courseId));
    }

    @Operation(summary = "코스 리뷰 페이지네이션 조회", description = "코스에 대한 리뷰를 페이지네이션합니다.")
    @GetMapping("/review/{courseId}")
    public ResponseEntity<ReviewPaginationResponseDto> getReviewPagination(
            @PathVariable long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(courseService.getReviewPagination(courseId,page,size));
    }

    @Operation(summary = "코스 별점,리뷰 생성 API", description = "코스 별점/리뷰를 생성합니다.")
    @PostMapping("/review")
    public ResponseEntity<Void> createCourseReview(Member member,@RequestBody CourseReviewCreateRequestDto request){
        courseService.createCourseReview(member, request);
        return ResponseEntity.noContent().build();
    }


}
