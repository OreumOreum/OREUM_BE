package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;

import java.util.List;

/**
 * 테스트용 Place 엔티티 픽처 클래스
 * 
 * 단위 테스트에서 사용하는 Place(장소) 객체를 생성하는 유틸리티 메소드를 제공합니다.
 * 제주도와 서귀포 지역의 샘플 관광지 데이터를 포함합니다.
 */
public class PlaceFixture {

    public static List<Place> places() {
        return List.of(
                Place.builder()
                        .title("제주관광지A").sigunguCode(4).build(),
                Place.builder()
                        .title("제주관광지B").sigunguCode(4).build(),
                Place.builder()
                        .title("서귀포관광지A").sigunguCode(3).build(),
                Place.builder()
                        .title("서귀포관광지B").sigunguCode(3).build()
        );
    }


}
