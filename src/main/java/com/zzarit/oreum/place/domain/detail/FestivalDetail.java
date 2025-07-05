package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "festival_detail")
public class FestivalDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "festivalDetail")
    private Place place;

    // 관람가능연령
    @Column(name = "agelimit")
    private String agelimit;

    // 예매처
    @Column(name = "bookingplace")
    private String bookingplace;

    // 할인 정보
    @Column(name = "discountinfofestival")
    private String discountinfofestival;

    // 행사 종료일
    @Column(name = "eventenddate")
    private String eventenddate;

    // 행사 홈페이지
    @Column(name = "eventhomepage")
    private String eventhomepage;

    // 행사 장소
    @Column(name = "eventplace")
    private String eventplace;

    // 행사 시작일
    @Column(name = "eventstartdate")
    private String eventstartdate;

    // 축제 등급
    @Column(name = "festivalgrade")
    private String festivalgrade;

    // 행사장 위치 안내
    @Column(name = "placeinfo")
    private String placeinfo;

    // 공연 시간
    @Column(name = "playtime")
    private String playtime;

    // 행사 프로그램
    @Column(name = "program")
    private String program;

    // 관람 소요 시간
    @Column(name = "spendtimefestival")
    private String spendtimefestival;

    // 주최자 정보
    @Column(name = "sponsor1")
    private String sponsor1;

    // 주최자 연락처
    @Column(name = "sponsor1tel")
    private String sponsor1tel;

    // 주관사 정보
    @Column(name = "sponsor2")
    private String sponsor2;

    // 주관사 연락처
    @Column(name = "sponsor2tel")
    private String sponsor2tel;

    // 부대 행사
    @Column(name = "subevent")
    private String subevent;

    // 이용 요금
    @Column(name = "usetimefestival")
    private String usetimefestival;

}