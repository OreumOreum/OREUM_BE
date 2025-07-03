package com.zzarit.oreum.place.dto;

public record LodgeDetailDto(
    String accomcountlodging, // 수용가능인원
    String checkintime, // 입실시간
    String checkouttime, // 퇴실시간
    String chkcooking, // 객실내취사여부
    String foodplace, // 식음료장
    String infocenterlodging, // 문의 및 안내
    String parkinglodging, // 주차시설
    String pickup, // 픽업서비스
    String roomcount, // 객실수
    String reservationlodging, // 예약안내
    String reservationurl, // 예약안내홈페이지
    String roomtype, // 객실유형
    String scalelodging, // 규모
    String subfacility, // 부대시설
    String barbecue, // 바비큐장여부
    String beauty, // 뷰티시설정보
    String beverage, // 식음료장여부
    String bicycle, // 자전거대여여부
    String campfire, // 캠프파이어여부
    String fitness, // 휘트니스센터여부
    String karaoke, // 노래방여부
    String publicbath, // 공용샤워실여부
    String publicpc, // 공용 PC실여부
    String sauna, // 사우나실여부
    String seminar, // 세미나실여부
    String sports, // 스포츠시설여부
    String refundregulation // 환불규정
) {}