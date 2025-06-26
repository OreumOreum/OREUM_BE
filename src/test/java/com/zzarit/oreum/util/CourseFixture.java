package com.zzarit.oreum.util;

import com.zzarit.oreum.place.domain.Course;

import java.util.List;
import java.util.stream.IntStream;

public class CourseFixture {
    public static List<Course> courses(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Course())
                .toList();
    }
}
