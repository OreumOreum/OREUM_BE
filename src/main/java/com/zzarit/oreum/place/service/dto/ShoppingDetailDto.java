package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.place.domain.detail.ShoppingDetail;

public record ShoppingDetailDto(
    String chkbabycarriageshopping, // 유모차대여정보
    String chkcreditcardshopping, // 신용카드가능정보
    String chkpetshopping, // 애완동물동반가능정보
    String culturecenter, // 문화센터바로가기
    String fairday, // 장서는날
    String infocentershopping, // 문의 및 안내
    String opendateshopping, // 개장일
    String opentime, // 영업시간
    String parkingshopping, // 주차시설
    String restdateshopping, // 쉬는날
    String restroom, // 화장실설명
    String saleitem, // 판매품목
    String saleitemcost, // 판매품목별가격
    String scaleshopping, // 규모
    String shopguide // 매장안내
) {
    public static ShoppingDetailDto from(ShoppingDetail detail) {
        return new ShoppingDetailDto(
                detail.getChkbabycarriageshopping(),
                detail.getChkcreditcardshopping(),
                detail.getChkpetshopping(),
                detail.getCulturecenter(),
                detail.getFairday(),
                detail.getInfocentershopping(),
                detail.getOpendateshopping(),
                detail.getOpentime(),
                detail.getParkingshopping(),
                detail.getRestdateshopping(),
                detail.getRestroom(),
                detail.getSaleitem(),
                detail.getSaleitemcost(),
                detail.getScaleshopping(),
                detail.getShopguide()
        );
    }
}