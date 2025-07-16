package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.detail.TourDetail;

public record TourDetailDto(
    String accomcount, // 수용인원
    String chkbabycarriage, // 유모차대여정보
    String chkcreditcard, // 신용카드가능정보
    String chkpet, // 애완동물동반가능정보
    String expagerange, // 체험가능연령
    String expguide, // 체험안내
    String heritage1, // 세계문화유산유무
    String heritage2, // 세계자연유산유무
    String heritage3, // 세계기록유산유무
    String infocenter, // 문의 및 안내
    String opendate, // 개장일
    String parking, // 주차시설
    String restdate, // 쉬는날
    String useseason, // 이용시기
    String usetime // 이용시간
) {
    public static TourDetailDto from(TourDetail detail) {
        return new TourDetailDto(
                detail.getAccomcount(),
                detail.getChkbabycarriage(),
                detail.getChkcreditcard(),
                detail.getChkpet(),
                detail.getExpagerange(),
                detail.getExpguide(),
                detail.getHeritage1(),
                detail.getHeritage2(),
                detail.getHeritage3(),
                detail.getInfocenter(),
                detail.getOpendate(),
                detail.getParking(),
                detail.getRestdate(),
                detail.getUseseason(),
                detail.getUsetime()
        );
    }
}