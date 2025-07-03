package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lodge_detail")
public class LodgeDetail extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "place_id")
    private Place place;


    // 수용 가능 인원
    @Column(name = "accomcountlodging")
    private String accomcountlodging;

    // 입실 시간
    @Column(name = "checkintime")
    private String checkintime;

    // 퇴실 시간
    @Column(name = "checkouttime")
    private String checkouttime;

    // 객실 내 취사 여부
    @Column(name = "chkcooking")
    private String chkcooking;

    // 식음료장
    @Column(name = "foodplace")
    private String foodplace;

    // 문의 및 안내
    @Column(name = "infocenterlodging")
    private String infocenterlodging;

    // 주차 시설
    @Column(name = "parkinglodging")
    private String parkinglodging;

    // 픽업 서비스
    @Column(name = "pickup")
    private String pickup;

    // 객실 수
    @Column(name = "roomcount")
    private String roomcount;

    // 예약 안내
    @Column(name = "reservationlodging")
    private String reservationlodging;

    // 예약 안내 홈페이지
    @Column(name = "reservationurl")
    private String reservationurl;

    // 객실 유형
    @Column(name = "roomtype")
    private String roomtype;

    // 규모
    @Column(name = "scalelodging")
    private String scalelodging;

    // 부대시설(기타)
    @Column(name = "subfacility")
    private String subfacility;

    // 바비큐장 여부
    @Column(name = "barbecue")
    private String barbecue;

    // 뷰티 시설 정보
    @Column(name = "beauty")
    private String beauty;

    // 식음료장 여부
    @Column(name = "beverage")
    private String beverage;

    // 자전거 대여 여부
    @Column(name = "bicycle")
    private String bicycle;

    // 캠프파이어 여부
    @Column(name = "campfire")
    private String campfire;

    // 휘트니스 센터 여부
    @Column(name = "fitness")
    private String fitness;

    // 노래방 여부
    @Column(name = "karaoke")
    private String karaoke;

    // 공용 샤워실 여부
    @Column(name = "publicbath")
    private String publicbath;

    // 공용 PC실 여부
    @Column(name = "publicpc")
    private String publicpc;

    // 사우나 여부
    @Column(name = "sauna")
    private String sauna;

    // 세미나실 여부
    @Column(name = "seminar")
    private String seminar;

    // 스포츠 시설 여부
    @Column(name = "sports")
    private String sports;

    // 환불 규정
    @Column(name = "refundregulation")
    private String refundregulation;

}