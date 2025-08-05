package com.zzarit.oreum.global.util;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    /**
     * 리스트에서 랜덤하게 하나의 요소를 반환합니다.
     * @param list 랜덤 추출할 리스트
     * @param <T>  리스트 요소 타입
     * @return      리스트가 비어있으면 null, 아니면 랜덤 요소
     */
    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        // 0 (inclusive) 부터 list.size() (exclusive) 사이의 인덱스를 랜덤 생성
        int randomIndex = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(randomIndex);
    }
}
