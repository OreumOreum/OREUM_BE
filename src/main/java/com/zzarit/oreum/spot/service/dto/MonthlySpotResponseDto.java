package com.zzarit.oreum.spot.service.dto;

import com.zzarit.oreum.spot.domain.Spot;
import lombok.Getter;

public record MonthlySpotResponseDto(
        Long spotId,
        Long placeId,
        String title,
        String address,
        String originImage,
        Integer sigunguCode,
        Long visitCount
) {



    static public MonthlySpotResponseDto from(Spot spot, Long count) {
        return new MonthlySpotResponseDto(
                spot.getId(),
                spot.getPlace().getId(),
                spot.getPlace().getTitle(),
                spot.getPlace().getAddress(),
                spot.getPlace().getOriginImage(),
                spot.getPlace().getSigunguCode(),
                count
        );
    }
}