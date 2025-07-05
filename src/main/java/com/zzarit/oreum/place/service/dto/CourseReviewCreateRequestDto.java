package com.zzarit.oreum.place.service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CourseReviewCreateRequestDto(
        @NotNull long courseId,
        @Min(value = 0, message = "평점은 최소 0점입니다.")
        @Max(value = 5, message = "평점은 최대 5점입니다.")
        @NotNull Double rate ,
        String content
) {
}
