package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Place;

public record PlaceDto(Long id,
                       String contentId,
                       String contentTypeId,
                       String title,
                       String address,
                       String thumbnailImage,
                       boolean isSaved
                       ) {

    public static PlaceDto from(Place place, boolean isSaved) {
        return new PlaceDto(
                place.getId(),
                place.getContentId(),
                place.getContentTypeId(),
                place.getTitle(),
                place.getAddress(),
                place.getThumbnailImage(),
                isSaved
        );
    }
}