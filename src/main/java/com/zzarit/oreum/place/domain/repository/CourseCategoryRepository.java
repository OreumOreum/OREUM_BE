package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
}