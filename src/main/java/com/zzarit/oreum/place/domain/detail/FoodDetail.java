package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "food_detail")
public class FoodDetail extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "place_id")
    private Place place;

    // 신용카드 가능 여부
    @Column(name = "chkcreditcardfood")
    private String chkcreditcardfood;

    // 할인 정보
    @Column(name = "discountinfofood")
    private String discountinfofood;

    // 대표 메뉴
    @Column(name = "firstmenu")
    private String firstmenu;

    // 문의 및 안내
    @Column(name = "infocenterfood")
    private String infocenterfood;

    // 어린이 놀이방 여부
    @Column(name = "kidsfacility")
    private String kidsfacility;

    // 개업일
    @Column(name = "opendatefood")
    private String opendatefood;

    // 영업 시간
    @Column(name = "opentimefood")
    private String opentimefood;

    // 포장 가능
    @Column(name = "packing")
    private String packing;

    // 주차 시설
    @Column(name = "parkingfood")
    private String parkingfood;

    // 예약 안내
    @Column(name = "reservationfood")
    private String reservationfood;

    // 쉬는 날
    @Column(name = "restdatefood")
    private String restdatefood;

    // 규모
    @Column(name = "scalefood")
    private String scalefood;

    // 좌석 수
    @Column(name = "seat")
    private String seat;

    // 금연/흡연 여부
    @Column(name = "smoking")
    private String smoking;

    // 취급 메뉴
    @Column(name = "treatmenu")
    private String treatmenu;

    // 인허가 번호
    @Column(name = "lcnsno")
    private String lcnsno;
}