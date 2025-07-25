package com.zzarit.oreum.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends OreumException {

    private static final String MESSAGE = "존재하지 않는 %s입니다.";
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String target) {
        super(String.format(MESSAGE, target), STATUS);
    }
}
