package com.zzarit.oreum.spot.service.dto;

import com.zzarit.oreum.spot.domain.Spot;

import java.time.LocalDate;
import java.time.Month;

public record StampReponseDto(
        Month month,
        int order
) {
    public static StampReponseDto from(Spot spot){
        return new StampReponseDto(spot.getDate().getMonth(),spot.getOrder());
    }
}
