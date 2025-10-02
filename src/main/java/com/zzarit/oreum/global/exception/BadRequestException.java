package com.zzarit.oreum.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 400 Bad Request 예외 클래스
 * 
 * 클라이언트의 잘못된 요청 매개변수나 데이터 형식으로 인해
 * 요청을 처리할 수 없을 때 발생하는 예외입니다.
 */
public class BadRequestException extends OreumException {

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message, STATUS);
    }
}
