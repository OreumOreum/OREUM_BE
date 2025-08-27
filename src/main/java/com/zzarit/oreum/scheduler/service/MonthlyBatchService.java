package com.zzarit.oreum.scheduler.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonthlyBatchService {

    private final PlaceRepository placeRepository;
    private final SpotRepository spotRepository;

    private static final int JEJU_SI_CODE = 4;
    private static final int SEOGWIPO_SI_CODE = 3;


    /**
     * 1. 이달의 여행지 선정 및 SPOT 테이블에 등록
     */
    public void selectAndCreateSpotsFor(LocalDate today) {
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        if (spotRepository.existsByDate(firstDayOfMonth)) {
            log.info("[이달의여행지] {}년 {}월의 여행지는 이미 존재하므로 생성을 건너뜁니다.", today.getYear(), today.getMonthValue());
            return; // 메소드를 즉시 종료
        }
        List<Long> excludedPlaceIds = spotRepository.findAllPlaceIds();

        List<Place> jejuPicks = pickRandomPlaces(JEJU_SI_CODE, 2, excludedPlaceIds);
        List<Place> seogwipoPicks = pickRandomPlaces(SEOGWIPO_SI_CODE, 2, excludedPlaceIds);

        AtomicInteger order = new AtomicInteger();
        List<Spot> newSpots = Stream.concat(seogwipoPicks.stream(), jejuPicks.stream())
                .map(place -> new Spot(firstDayOfMonth, place, order.getAndIncrement()))
                .collect(Collectors.toList());

        spotRepository.saveAll(newSpots);
        log.info("[이달의여행지] {}년 {}월의 여행지 {}곳을 SPOT에 등록했습니다.", today.getYear(), today.getMonthValue(), newSpots.size());
    }

    private List<Place> pickRandomPlaces(int sigunguCode, int count, List<Long> excludedIds) {
        List<Place> candidates = excludedIds.isEmpty()
                ? placeRepository.findBySigunguCode(sigunguCode)
                : placeRepository.findBySigunguCodeAndIdNotIn(sigunguCode, excludedIds);


        if (candidates.size() < count) {
            log.warn("[이달의여행지] {} 지역에 추천할 장소가 부족합니다. (요청: {}, 후보: {})", sigunguCode, count, candidates.size());
            throw new NotFoundException("추천할 장소");
        }
        Collections.shuffle(candidates);
        return candidates.subList(0, count);
    }
}