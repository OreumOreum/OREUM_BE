package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Place;

public record PlacePaginationResponseDto(
        Long id,
        String title,
        String contentId,
        String contentTypeId,
        String originImage,
        String thumbnailImage,
        Integer sigunguCode,
        boolean isSaved

) {

    public static PlacePaginationResponseDto from(Place place, boolean isSaved) {
        return new PlacePaginationResponseDto(
                place.getId(),
                place.getTitle(),
                place.getContentId(),
                place.getContentTypeId(),
                place.getOriginImage(),
                place.getThumbnailImage(),
                place.getSigunguCode(),
                isSaved
        );
    }


}