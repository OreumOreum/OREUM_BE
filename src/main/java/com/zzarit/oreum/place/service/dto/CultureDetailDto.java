package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.detail.CultureDetail;

public record CultureDetailDto(
    String accomcountculture,
    String chkbabycarriageculture,
    String chkcreditcardculture,
    String chkpetculture,
    String discountinfo,
    String infocenterculture,
    String parkingculture,
    String parkingfee,
    String restdateculture,
    String usefee,
    String usetimeculture,
    String scale,
    String spendtime
) {
    public static CultureDetailDto from(CultureDetail detail) {
        return new CultureDetailDto(
                detail.getAccomcountculture(),
                detail.getChkbabycarriageculture(),
                detail.getChkcreditcardculture(),
                detail.getChkpetculture(),
                detail.getDiscountinfo(),
                detail.getInfocenterculture(),
                detail.getParkingculture(),
                detail.getParkingfee(),
                detail.getRestdateculture(),
                detail.getUsefee(),
                detail.getUsetimeculture(),
                detail.getScale(),
                detail.getSpendtime()
        );
    }
}