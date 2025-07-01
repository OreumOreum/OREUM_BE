package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Place;

public record PlaceListResponseDto(long id, String thumbnailImage,
                                   String category1,
                                   String category2,
                                   String category3) {
    public static PlaceListResponseDto from(Place place) {
        return new PlaceListResponseDto(
                place.getId(),
                place.getThumbnailImage(),
                place.getCategory1(),
                place.getCategory2(),
                place.getCategory3()
            );
    }

}