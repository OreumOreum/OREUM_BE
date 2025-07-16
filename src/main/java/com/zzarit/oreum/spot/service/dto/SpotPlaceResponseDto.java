package com.zzarit.oreum.spot.service.dto;

import com.zzarit.oreum.spot.domain.Spot;

public record SpotPlaceResponseDto(
        Long id,
        String title,
        String address,
        String detailAddress,
        double mapX,
        double mapY,
        boolean visited
)
{
    public static SpotPlaceResponseDto from(Spot spot,boolean visited){
        return new SpotPlaceResponseDto(
                spot.getPlace().getId(),
                spot.getPlace().getTitle(),
                spot.getPlace().getAddress(),
                spot.getPlace().getDetailAddress(),
                spot.getPlace().getMapx(),
                spot.getPlace().getMapy(),
                visited
        );
    }
}
