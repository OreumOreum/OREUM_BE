package com.oreum.zzarit.place.domain.repository;

import com.oreum.zzarit.place.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}