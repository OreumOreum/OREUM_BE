package com.oreum.zzarit.global.exception.handler;

import com.oreum.zzarit.global.exception.OreumException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final UncatchedExceptionHandler handler;

    @ExceptionHandler(OreumException.class)
    public ResponseEntity<ProblemDetail> handleCustomException(OreumException e) {
        HttpServletRequest request = getCurrentHttpRequest();
        ExceptionLogger.info(request, e);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpectedException(Exception e, WebRequest request) {
        ProblemDetail problemDetail = handleException(e, request);
        HttpStatus statusCode = HttpStatus.valueOf(problemDetail.getStatus());
        if (statusCode.is5xxServerError()) {
            ExceptionLogger.error(problemDetail);
        } else {
            ExceptionLogger.warn(problemDetail);
        }
        ProblemDetail detailsToSend = ProblemDetail.forStatus(statusCode);
        return ResponseEntity.of(detailsToSend).build();
    }

    private ProblemDetail handleException(Exception e, WebRequest request) {
        try {
            ProblemDetail problemDetail = (ProblemDetail) Objects.requireNonNull(handler.handleException(e, request))
                    .getBody();
            problemDetail.setProperties(setDetails(getCurrentHttpRequest(), e, HttpStatus.INTERNAL_SERVER_ERROR));
            return problemDetail;
        } catch (Exception ex) {
            return ProblemDetail.forStatusAndDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "예기치 못한 오류가 발생하였습니다."
            );
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }



    private static Map<String, Object> setDetails(HttpServletRequest request, Exception exception, HttpStatus status) {
        StackTraceElement origin = exception.getStackTrace()[0];
        Map<String, Object> map = new HashMap<>();
        map.put("httpMethod", request.getMethod());
        map.put("requestUri", request.getRequestURI());
        map.put("statusCode", status.toString());
        map.put("sourceClass", origin.getClassName());
        map.put("sourceMethod", origin.getMethodName());
        map.put("exceptionClass", exception.getClass().getSimpleName());
        map.put("exceptionMessage", exception.getMessage());
        return map;
    }
}
