package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.place.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}