package com.zzarit.oreum.scheduler.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
    @Scheduled(cron = "0 0 3 * * ?")
    public void dailySynchronization() {
        dailySynchronizeService.syncDaily();
    }


    /**
     * 매일 낮 12시 500건씩 Place/Course Overview 갱신(Dev버전)
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void saveOverviewDaily() {
        synchronizeService.saveOverviewBatchDevelopVersion();
    }

    /**
     * 매달 1일 새벽 00시에 월별 배치 작업 실행
     * cron = "초 분 시 일 월 요일"
     */
    @Scheduled(cron = "0 0 0 1 * *")
    //@Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void executeMonthlyProcess() {
        log.info("[이달의여행지] 월별 배치 작업을 시작합니다.");

        LocalDate today = LocalDate.now();
        monthlyBatchService.selectAndCreateSpotsFor(today);

        log.info("[이달의여행지] 월별 배치 작업이 성공적으로 완료되었습니다.");
    }
}
