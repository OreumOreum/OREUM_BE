package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.service.dto.RateSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("""
            SELECT new com.zzarit.oreum.place.service.dto.RateSummary(
            AVG (r.rate),
            COUNT (r)           
            )
            FROM Review r
            WHERE r.course.id = :courseId
            """)
    RateSummary getRateSummaryByCourseId(Long courseId);

    Page<Review> findByCourse(Course course, Pageable pageable);

    Page<Review> findByPlace(Place place, Pageable pageable);
}