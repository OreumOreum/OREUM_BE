package com.zzarit.oreum.place.service.dto;

import java.time.LocalDateTime;

public record MyReviewResponseDto(
        long reviewID,
        Double rate,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String placeTitle
) {
}
