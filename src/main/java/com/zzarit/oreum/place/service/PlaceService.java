package com.zzarit.oreum.place.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;

    public Page<Place> getSearchPlaces(PlaceSearchConditionDto condition, Pageable pageable) {
        return placeRepository.searchPlaces(condition, pageable);
    }

    @Transactional
    public void createReviewAndRating(Member member, ReviewCreateRequestDto request) {
        Place place = placeRepository.findById(request.placeId()).orElseThrow(
                () -> new NotFoundException("장소")
        );

        Review review = new Review(request.content(),request.rate(),place,member);

        reviewRepository.save(review);
    }

    @Transactional
    public void createCourseReview(Member member, CourseReviewCreateRequestDto request) {
        Course course = courseRepository.findById(request.courseId()).orElseThrow(
                () -> new NotFoundException("코스")
        );

        Review review = new Review(request.content(),request.rate(),course,member);

        reviewRepository.save(review);
    }

    public List<CourseResponseDto> getCourseList(Member member){
        List<Course> courses;
        Category category = member.getCategory();

        if (category == null) {
            courses = courseRepository.findAll();
        } else {
            courses = courseRepository.findAllByCategoryType(category.getType());
        }

        return courses.stream()
                .map(CourseResponseDto::from)
                .toList();
    }

    @Transactional
    public CourseDetailResponseDto getCourseDetail(Long id){
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("코스"));
        return CourseDetailResponseDto.from(course);
    }
}
