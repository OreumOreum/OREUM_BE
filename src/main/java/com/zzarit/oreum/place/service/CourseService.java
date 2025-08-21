package com.zzarit.oreum.place.service;


import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private final ReviewRepository reviewRepository;
    private final CourseRepository courseRepository;


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
        RateSummary rateSummary = reviewRepository.getRateSummaryByCourseId(course.getId());
        return CourseDetailResponseDto.from(course,rateSummary);
    }

    @Transactional
    public ReviewPaginationResponseDto getReviewPagination(long courseId, int page, int size, Member member){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("코스"));
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdAt").descending());
        Page<Review> reviews = reviewRepository.findByCourse(course, pageable);

        List<ReviewResponseDto> dtos = reviews.getContent().stream()
                .map((review) -> {
                    boolean isMyReview = Objects.equals(review.getMember().getId(), member.getId());
                    return ReviewResponseDto.from(review, isMyReview);
                })
                .sorted(Comparator.comparing(ReviewResponseDto::isMyReview).reversed())
                .toList();

        RateSummary rateSummary = reviewRepository.getRateSummaryByCourseId(courseId);

        return new ReviewPaginationResponseDto(dtos,rateSummary.average(), reviews.getTotalElements(),reviews.isLast());
    }
}
