package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leports_detail")
public class LeportsDetail extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "place_id")
    private Place place;


    // 수용 인원
    @Column(name = "accomcountleports")
    private Integer accomcountleports;

    // 유모차 대여 정보
    @Column(name = "chkbabycarriageleports")
    private String chkbabycarriageleports;

    // 신용카드 가능 여부
    @Column(name = "chkcreditcardleports")
    private String chkcreditcardleports;

    // 애완동물 동반 가능 여부
    @Column(name = "chkpetleports")
    private String chkpetleports;

    // 체험 가능 연령
    @Column(name = "expagerangeleports")
    private String expagerangeleports;

    // 문의 및 안내
    @Column(name = "infocenterleports")
    private String infocenterleports;

    // 개장 기간
    @Column(name = "openperiod")
    private String openperiod;

    // 주차 요금
    @Column(name = "parkingfeeleports")
    private String parkingfeeleports;

    // 주차 시설
    @Column(name = "parkingleports")
    private String parkingleports;

    // 예약 안내
    @Column(name = "reservation")
    private String reservation;

    // 쉬는 날
    @Column(name = "restdateleports")
    private String restdateleports;

    // 규모
    @Column(name = "scaleleports")
    private String scaleleports;

    // 입장료
    @Column(name = "usefeeleports")
    private String usefeeleports;

    // 이용 시간
    @Column(name = "usetimeleports")
    private String usetimeleports;

}