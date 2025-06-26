package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.CourseCategory;

import java.util.List;

public class CourseCategoryFixture {
    public static CourseCategory courseCategory(Course course, Category category) {
        CourseCategory cc = new CourseCategory();
        cc.setCourse(course);
        cc.setCategory(category);
        return cc;
    }

    public static List<CourseCategory> linkCoursesToCategory(List<Course> courses, Category category) {
        return courses.stream()
                .map(course -> {
                    CourseCategory cc = new CourseCategory();
                    cc.setCourse(course);
                    cc.setCategory(category);
                    return cc;
                })
                .toList();
    }
}
