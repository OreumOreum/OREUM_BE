package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.detail.FestivalDetail;

public record FestivalDetailDto(
    String agelimit,
    String bookingplace,
    String discountinfofestival,
    String eventenddate,
    String eventhomepage,
    String eventplace,
    String eventstartdate,
    String festivalgrade,
    String placeinfo,
    String playtime,
    String program,
    String spendtimefestival,
    String sponsor1,
    String sponsor1tel,
    String sponsor2,
    String sponsor2tel,
    String subevent,
    String usetimefestival
) {
    public static FestivalDetailDto from(FestivalDetail detail) {
        return new FestivalDetailDto(
                detail.getAgelimit(),
                detail.getBookingplace(),
                detail.getDiscountinfofestival(),
                detail.getEventenddate(),
                detail.getEventhomepage(),
                detail.getEventplace(),
                detail.getEventstartdate(),
                detail.getFestivalgrade(),
                detail.getPlaceinfo(),
                detail.getPlaytime(),
                detail.getProgram(),
                detail.getSpendtimefestival(),
                detail.getSponsor1(),
                detail.getSponsor1tel(),
                detail.getSponsor2(),
                detail.getSponsor2tel(),
                detail.getSubevent(),
                detail.getUsetimefestival()
        );
    }
}