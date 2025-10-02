package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.CourseCategory;

import java.util.List;

/**
 * 테스트용 CourseCategory 엔티티 픽처 클래스
 * 
 * 단위 테스트에서 사용하는 CourseCategory(코스-카테고리 매핑) 객체를 생성하는
 * 유틸리티 메소드를 제공합니다. 코스와 카테고리 간의 연결 관계를 테스트에서 쉽게 만들 수 있습니다.
 */
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
