package com.zzarit.oreum.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SyncRunner implements ApplicationRunner {
    private final SynchronizeService synchronizeService;

    @Override
    public void run(ApplicationArguments args) {
        synchronizeService.initialize();
    }
}
