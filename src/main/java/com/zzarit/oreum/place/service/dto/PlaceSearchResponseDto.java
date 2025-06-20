package com.zzarit.oreum.place.service.dto;

import java.util.List;

public record PlaceSearchResponseDto(
        List<PlaceDto> content,
        boolean isLastPage
) {}