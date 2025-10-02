package com.zzarit.oreum.logging.support;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * MDC 문맥 전파를 위한 Task 데코레이터
 * 
 * 비동기 태스크 실행 시 MDC 문맥을 상속받아 로깅의 일관성을 보장합니다.
 * TrackId 등의 로깅 문맥 정보를 비동기 스레드로 전파함으로써
 * 전체 요청 사이클에서 로그를 추적할 수 있도록 합니다.
 */
public class MdcTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            if (contextMap != null) {
                MDC.setContextMap(contextMap);
            }
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
