package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shopping_deatail")
public class ShoppingDeatail extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "place_id")
    private Place place;

    // 유모차 대여 정보
    @Column(name = "chkbabycarriageshopping")
    private String chkbabycarriageshopping;

    // 신용카드 가능 여부
    @Column(name = "chkcreditcardshopping")
    private String chkcreditcardshopping;

    // 애완동물 동반 가능 여부
    @Column(name = "chkpetshopping")
    private String chkpetshopping;

    // 문화센터 바로가기
    @Column(name = "culturecenter")
    private String culturecenter;

    // 장서는 날
    @Column(name = "fairday")
    private String fairday;

    // 문의 및 안내
    @Column(name = "infocentershopping")
    private String infocentershopping;

    // 개장일
    @Column(name = "opendateshopping")
    private String opendateshopping;

    // 영업 시간
    @Column(name = "opentime")
    private String opentime;

    // 주차 시설
    @Column(name = "parkingshopping")
    private String parkingshopping;

    // 쉬는 날
    @Column(name = "restdateshopping")
    private String restdateshopping;

    // 화장실 설명
    @Column(name = "restroom")
    private String restroom;

    // 판매 품목
    @Column(name = "saleitem")
    private String saleitem;

    // 판매 품목별 가격
    @Column(name = "saleitemcost")
    private String saleitemcost;

    // 규모
    @Column(name = "scaleshopping")
    private String scaleshopping;

    // 매장 안내
    @Column(name = "shopguide")
    private String shopguide;

}