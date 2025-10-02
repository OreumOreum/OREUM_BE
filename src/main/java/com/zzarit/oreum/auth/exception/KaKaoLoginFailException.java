package com.zzarit.oreum.auth.exception;

import com.zzarit.oreum.global.exception.OreumException;
import org.springframework.http.HttpStatus;

/**
 * 카카오 로그인 실패 예외
 * 
 * 카카오 API 호출 실패 시 발생하는 예외입니다.
 */
public class KaKaoLoginFailException extends OreumException {

    private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public KaKaoLoginFailException(String message) {
        super(message, STATUS);
    }
}
