package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "culture_detail")
public class CultureDetail extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "place_id")
    private Place place;

    // 수용 인원
    @Column(name = "accomcountculture")
    private String accomcountculture;

    // 유모차 대여 정보
    @Column(name = "chkbabycarriageculture")
    private String chkbabycarriageculture;

    // 신용카드 가능 여부
    @Column(name = "chkcreditcardculture")
    private String chkcreditcardculture;

    // 애완동물 동반 가능 여부
    @Column(name = "chkpetculture")
    private String chkpetculture;

    // 할인 정보
    @Column(name = "discountinfo")
    private String discountinfo;

    // 문의 및 안내
    @Column(name = "infocenterculture")
    private String infocenterculture;

    // 주차 시설
    @Column(name = "parkingculture")
    private String parkingculture;

    // 주차 요금
    @Column(name = "parkingfee")
    private String parkingfee;

    // 쉬는 날
    @Column(name = "restdateculture")
    private String restdateculture;

    // 이용 요금
    @Column(name = "usefee")
    private String usefee;

    // 이용 시간
    @Column(name = "usetimeculture")
    private String usetimeculture;

    // 규모
    @Column(name = "scale")
    private String scale;

    // 관람 소요 시간
    @Column(name = "spendtime")
    private String spendtime;
}