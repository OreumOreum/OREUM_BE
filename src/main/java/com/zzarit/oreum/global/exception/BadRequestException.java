package com.zzarit.oreum.global.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends OreumException {

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message, STATUS);
    }
}
