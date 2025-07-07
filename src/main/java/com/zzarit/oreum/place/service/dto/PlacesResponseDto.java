package com.zzarit.oreum.place.service.dto;

import java.util.List;

public record PlacesResponseDto(
        List<PlacePaginationResponseDto> places,
        boolean isLastPage
) {
}
