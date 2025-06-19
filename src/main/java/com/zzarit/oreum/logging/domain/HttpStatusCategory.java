package com.zzarit.oreum.logging.domain;


import com.zzarit.oreum.global.exception.InternalServerException;
import com.zzarit.oreum.logging.dto.LogContext;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import java.util.Arrays;

@RequiredArgsConstructor
public enum HttpStatusCategory {

    INFO_SUCCESS(200, 299) {
        @Override
        public void log(Logger logger, LogContext context) {
            logger.info(formatLog(context));
        }
    },
    INFO_REDIRECT(300, 399) {
        @Override
        public void log(Logger logger, LogContext context) {
            logger.info(formatLog(context));
        }
    },
    INFO_FAIL(400, 499) {
        @Override
        public void log(Logger logger, LogContext context) {
            logger.warn(formatLog(context));
        }
    },
    WARN(500, 599) {
        @Override
        public void log(Logger logger, LogContext context) {
            logger.error(formatLog(context));
        }
    };

    private static final String LOG_FORMAT = "trackId=%s, memberId=%s, method=%s, uri=%s, requestBody=%s, statusCode=%s, latency=%s, responseBody=%s, stackTrace=%s";
    private final int min;
    private final int max;

    public abstract void log(Logger logger, LogContext context);


    public static HttpStatusCategory fromStatusCode(int statusCode) {
        return Arrays.stream(values())
                .filter(category -> statusCode >= category.min && statusCode <= category.max)
                .findFirst()
                .orElseThrow(() -> new InternalServerException("해당 상태는 존재하지 않습니다."));
    }


    protected String formatLog(LogContext context) {
        return LOG_FORMAT.formatted(context.identifier(),
                context.memberIdentifier().getIdInfo(),
                context.httpMethod(),
                context.uri(),
                context.requestBody(),
                context.statusCode(),
                context.latency(),
                context.responseBody(),
                context.stackTrace());
    }
}
