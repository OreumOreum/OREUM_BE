package com.zzarit.oreum.spot.service.dto;

import com.zzarit.oreum.spot.domain.Spot;
import lombok.Getter;

@Getter
public class MonthlySpotResponseDto {
    private final Long spotId;
    private final Long placeId;
    private final String title;
    private final String address;
    private final String thumbnailImage;
    private final int sigunguCode;

    public MonthlySpotResponseDto(Spot spot) {
        this.spotId = spot.getId();
        this.placeId = spot.getPlace().getId();
        this.title = spot.getPlace().getTitle();
        this.address = spot.getPlace().getAddress();
        this.thumbnailImage = spot.getPlace().getThumbnailImage();
        this.sigunguCode = spot.getPlace().getSigunguCode();
    }
}