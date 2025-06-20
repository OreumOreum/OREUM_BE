package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Place;

public record PlaceDto(Long id, String title, String address, String thumbnailImage) {

    public static PlaceDto from(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getTitle(),
                place.getAddress(),
                place.getThumbnailImage()
        );
    }
}