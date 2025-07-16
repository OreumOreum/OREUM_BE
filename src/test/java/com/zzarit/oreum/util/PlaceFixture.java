package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;

import java.util.List;

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
