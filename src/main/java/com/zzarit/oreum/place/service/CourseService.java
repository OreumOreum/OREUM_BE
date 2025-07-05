package com.zzarit.oreum.place.service;


import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.CourseDetailResponseDto;
import com.zzarit.oreum.place.service.dto.CourseResponseDto;
import com.zzarit.oreum.place.service.dto.CourseReviewCreateRequestDto;
import com.zzarit.oreum.place.service.dto.RateSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
