package com.zzarit.oreum.planner.service.dto;

import com.zzarit.oreum.place.domain.Place;

import java.util.List;

public record PlannerCourseResponseDto(
        String plannerName,
        List<CoursePlaceDto> placeList
) {
    public static PlannerCourseResponseDto of(String plannerName, List<Place> places) {
        List<CoursePlaceDto> dtos = places.stream()
                .map(place -> new CoursePlaceDto(
                        place.getId(),
                        place.getContentTypeId(),
                        place.getContentId(),
                        place.getTitle(),
                        place.getAddress(),
                        place.getThumbnailImage())
                )
                .toList();
        return new PlannerCourseResponseDto(plannerName, dtos);
    }

    public record CoursePlaceDto(
            Long placeId,
            String contentTypeId,
            String contentId,
            String placeTitle,
            String placeAddress,
            String placeThumbnailImage
    ) {}
}
