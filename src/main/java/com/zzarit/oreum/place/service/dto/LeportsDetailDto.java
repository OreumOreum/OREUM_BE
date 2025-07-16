package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.detail.LeportsDetail;

public record LeportsDetailDto(
    String accomcountleports,
    String chkbabycarriageleports,
    String chkcreditcardleports,
    String chkpetleports,
    String expagerangeleports,
    String infocenterleports,
    String openperiod,
    String parkingfeeleports,
    String parkingleports,
    String reservation,
    String restdateleports,
    String scaleleports,
    String usefeeleports,
    String usetimeleports
) {
    public static LeportsDetailDto from(LeportsDetail detail) {
        return new LeportsDetailDto(
                detail.getAccomcountleports(),
                detail.getChkbabycarriageleports(),
                detail.getChkcreditcardleports(),
                detail.getChkpetleports(),
                detail.getExpagerangeleports(),
                detail.getInfocenterleports(),
                detail.getOpenperiod(),
                detail.getParkingfeeleports(),
                detail.getParkingleports(),
                detail.getReservation(),
                detail.getRestdateleports(),
                detail.getScaleleports(),
                detail.getUsefeeleports(),
                detail.getUsetimeleports()
        );
    }
}