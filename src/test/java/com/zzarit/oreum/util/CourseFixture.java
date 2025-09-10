package com.zzarit.oreum.util;

import com.zzarit.oreum.place.domain.Course;

import java.util.List;
import java.util.stream.IntStream;

/**
 * 테스트용 Course 엔티티 픽처 클래스
 * 
 * 단위 테스트에서 사용하는 Course(여행 코스) 객체를 생성하는 유틸리티 메소드를 제공합니다.
 * 지정된 개수만큼의 Course 객체 리스트를 생성할 수 있습니다.
 */
public class CourseFixture {
    public static List<Course> courses(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Course())
                .toList();
    }
}
