package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tour_detail")
public class TourDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "tourDetail")
    private Place place;

    // 수용 인원
    @Column(name = "accomcount")
    private String accomcount;

    // 유모차 대여 정보
    @Column(name = "chkbabycarriage")
    private String chkbabycarriage;

    // 신용카드 가능 여부
    @Column(name = "chkcreditcard")
    private String chkcreditcard;

    // 애완동물 동반 가능 여부
    @Column(name = "chkpet")
    private String chkpet;

    // 체험 가능 연령
    @Column(name = "expagerange")
    private String expagerange;

    // 체험 안내
    @Column(name = "expguide")
    private String expguide;

    // 세계문화유산 유무
    @Column(name = "heritage1")
    private Integer heritage1;

    // 세계자연유산 유무
    @Column(name = "heritage2")
    private String heritage2;

    // 세계기록유산 유무
    @Column(name = "heritage3")
    private String heritage3;

    // 문의 및 안내
    @Column(name = "infocenter")
    private String infocenter;

    // 개장일
    @Column(name = "opendate")
    private String opendate;

    // 주차 시설 정보
    @Column(name = "parking")
    private String parking;

    // 쉬는 날
    @Column(name = "restdate")
    private String restdate;

    // 이용 시기
    @Column(name = "useseason")
    private String useseason;

    // 이용 시간
    @Column(name = "usetime")
    private String usetime;

}