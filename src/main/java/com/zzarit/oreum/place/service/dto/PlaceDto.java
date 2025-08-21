package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Place;

public record PlaceDto(Long id,
                       String contentId,
                       String contentTypeId,
                       String title,
                       String address,
                       String thumbnailImage) {

    public static PlaceDto from(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getContentId(),
                place.getContentTypeId(),
                place.getTitle(),
                place.getAddress(),
                place.getThumbnailImage()
        );
    }
}