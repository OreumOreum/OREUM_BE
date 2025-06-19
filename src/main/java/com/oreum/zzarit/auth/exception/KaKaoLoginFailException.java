package com.oreum.zzarit.auth.exception;

import com.oreum.zzarit.global.exception.OreumException;
import org.springframework.http.HttpStatus;

public class KaKaoLoginFailException extends OreumException {

    private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public KaKaoLoginFailException(String message) {
        super(message, STATUS);
    }
}
