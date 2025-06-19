package com.zzarit.oreum.global.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends OreumException {

    private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(message, STATUS);
    }
}
