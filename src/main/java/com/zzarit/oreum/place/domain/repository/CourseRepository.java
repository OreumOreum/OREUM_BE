package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("""
        SELECT c
        FROM Course c
        JOIN c.courseCategories cc
        JOIN cc.category cat
        WHERE cat.type = :type
""")
    public List<Course> findAllByCategoryType(Type type);


    @Query("select c.contentId from Course c where c.contentId in :ids")
    List<String> findAllContentIdIn(@Param("ids") Collection<String> ids);
}