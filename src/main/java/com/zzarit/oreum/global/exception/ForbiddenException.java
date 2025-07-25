package com.zzarit.oreum.global.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends OreumException {

    private static final String MESSAGE = "접근 권한이 없습니다";
    private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;

    public ForbiddenException() {
        super(MESSAGE, STATUS);
    }
}
