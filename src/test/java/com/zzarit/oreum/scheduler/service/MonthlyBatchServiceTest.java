package com.zzarit.oreum.scheduler.service;

import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.repository.SpotRepository;
import com.zzarit.oreum.util.PlaceFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonthlyBatchServiceTest {

    @Autowired
    private MonthlyBatchService monthlyBatchService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Test
    @Transactional
    void spotSelectTest(){
        placeRepository.saveAll(PlaceFixture.places());
        monthlyBatchService.executeMonthlyProcess();
    }

}