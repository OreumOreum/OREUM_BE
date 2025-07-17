package com.zzarit.oreum.place.service.dto;

import java.util.List;

public record ReviewPaginationResponseDto(
        List<ReviewResponseDto> reviews,
        long total,
        boolean isLastPage
) {
}
