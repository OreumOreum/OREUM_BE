package com.zzarit.oreum.scheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * 시스템 스케줄예 관리 클래스
 * 
 * 주정적으로 실행되는 배치 작업들을 관리합니다.
 * 매일 데이터 동기화, 이달의 여행지 선정, 상세 설명 갱신 등의
 * 주기적 작업들을 자동으로 수행합니다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerManager {
    private final DailySynchronizeService dailySynchronizeService;
    private final MonthlyBatchService monthlyBatchService;
    private final SynchronizeService synchronizeService;


    /**
     * 매일 새벽 3시 DataBase 동기화
     */
    @Scheduled(cron = "0 0 3 * * ?", zone = "Asia/Seoul")
    public void dailySynchronization() {
        dailySynchronizeService.syncDaily();
    }



    /**
     * 매일 12시에 이달의 여행지 갱신 (이달의 여행지 있으면 스킵)
     */
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul")
    @Transactional
    public void executeMonthlyProcess() {
        log.info("[이달의여행지] 월별 배치 작업을 시작합니다.");

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        monthlyBatchService.selectAndCreateSpotsFor(today);

        log.info("[이달의여행지] 월별 배치 작업이 성공적으로 완료되었습니다.");
    }
}
