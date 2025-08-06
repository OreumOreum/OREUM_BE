package com.zzarit.oreum.scheduler.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SynchronizeServiceTest {

    @Autowired
    private SynchronizeService synchronizeService;

    @Test
    void save(){
        synchronizeService.savePlaceAndCourse();
    }

    @Test
    void saveOverview(){
        synchronizeService.saveOverviewBatch();
    }

    @Test
    void saveCoursePlace(){
        synchronizeService.saveCoursePlace();
    }

}