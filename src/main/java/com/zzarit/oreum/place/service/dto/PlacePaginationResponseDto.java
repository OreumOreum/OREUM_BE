package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.Place;

public record PlacePaginationResponseDto(
        Long id,
        String title,
        String contentTypeId,
        String originImage,
        String thumbnailImage,
        Integer sigunguCode
) {

    public static PlacePaginationResponseDto from(Place place) {
        return new PlacePaginationResponseDto(
                place.getId(),
                place.getTitle(),
                place.getContentTypeId(),
                place.getOriginImage(),
                place.getThumbnailImage(),
                place.getSigunguCode()
        );
    }


}