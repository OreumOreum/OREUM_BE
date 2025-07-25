package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Course;

public record CourseResponseDto(
        Long id,
        String title,
        String originImage,
        String thumbnailImage,
        String category2,
        Integer sigunguCode
) {

    public static CourseResponseDto from(Course course) {
        return new CourseResponseDto(
                course.getId(),
                course.getTitle(),
                course.getOriginImage(),
                course.getThumbnailImage(),
                course.getCategory2(),
                course.getSigunguCode()
        );
    }


}


