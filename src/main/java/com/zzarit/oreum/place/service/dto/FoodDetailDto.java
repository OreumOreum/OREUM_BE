package com.zzarit.oreum.place.service.dto;

public record FoodDetailDto(
    String chkcreditcardfood, // 신용카드가능정보
    String discountinfofood, // 할인정보
    String firstmenu, // 대표메뉴
    String infocenterfood, // 문의 및 안내
    String kidsfacility, // 어린이놀이방여부
    String opendatefood, // 개업일
    String opentimefood, // 영업시간
    String packing, // 포장가능
    String parkingfood, // 주차시설
    String reservationfood, // 예약안내
    String restdatefood, // 쉬는날
    String scalefood, // 규모
    String seat, // 좌석수
    String smoking, // 금연/흡연여부
    String treatmenu, // 취급메뉴
    String lcnsno // 인허가번호
) {}