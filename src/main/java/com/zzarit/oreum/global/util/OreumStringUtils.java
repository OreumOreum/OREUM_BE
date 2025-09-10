package com.zzarit.oreum.global.util;

import java.util.Arrays;

/**
 * OREUM 프로젝트 전용 문자열 유틸리티 클래스
 * 
 * 문자열 처리와 관련된 공통 기능을 제공합니다.
 * 주로 주소 데이터 처리에 사용됩니다.
 */
public class OreumStringUtils {

    public static String removePrefix(String fullAddr) {
        if(fullAddr == null) return null;

        String[] token = fullAddr.split(" ");
        return String.join(" ", Arrays.copyOfRange(token, 1, token.length));
    }
}
