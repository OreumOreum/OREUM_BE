package com.zzarit.oreum.place.service.dto;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;

import java.util.List;

public record PlaceDetailResponseDto(
        String title,
        String smallCategory,
        String middleCategory,
        String largeCategory,
        String address,
        Integer sigunguCode,
        String overview,
        Double mapX,
        Double mapY,
        Object detailInfo,
        Double averageRate,
        Long reviewCount,
        boolean isSpot,
        boolean isSaved
) {

    public static PlaceDetailResponseDto from(Place place, RateSummary rateSummary, boolean isSpot, boolean isSaved) {

        return new PlaceDetailResponseDto(
                place.getTitle(),
                place.getCategory3(),
                place.getCategory2(),
                place.getCategory1(),
                place.getAddress(),
                place.getSigunguCode(),
                place.getOverview(),
                place.getMapx(),
                place.getMapy(),
                place.getDetailInfo(),
                rateSummary.average(),
                rateSummary.count(),
                isSpot,
                isSaved
                );


    }


}



