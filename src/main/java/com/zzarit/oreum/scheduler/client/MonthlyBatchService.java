package com.zzarit.oreum.scheduler.client;

import com.zzarit.oreum.member.domain.repository.CategoryRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.spot.domain.Badge;
import com.zzarit.oreum.spot.domain.SpotCategorySummary;
import com.zzarit.oreum.spot.domain.repository.BadgeRepository;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.repository.SpotCategorySummaryRepository;
import com.zzarit.oreum.spot.domain.repository.SpotRepository;
import com.zzarit.oreum.spot.domain.repository.VisitLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
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
    private final VisitLogRepository visitLogRepository;
    private final SpotCategorySummaryRepository spotCategorySummaryRepository;
    private final BadgeRepository badgeRepository;
    private final CategoryRepository categoryRepository;

    private static final int JEJU_SI_CODE = 4;
    private static final int SEOGWIPO_SI_CODE = 3;

    /**
     * 매달 1일 새벽 2시에 월별 배치 작업 실행
     * cron = "초 분 시 일 월 요일"
     */
    @Scheduled(cron = "0 0 2 1 * *")
    //@Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void executeMonthlyProcess() {
        log.info("월별 배치 작업을 시작합니다.");

        LocalDate today = LocalDate.now();
        YearMonth lastMonth = YearMonth.from(today.minusMonths(1));

        selectAndCreateSpotsFor(today);
        processRewardsFor(lastMonth);
        log.info("월별 배치 작업이 성공적으로 완료되었습니다.");
    }
    private void processRewardsFor(YearMonth lastMonth) {
        LocalDate firstDay = lastMonth.atDay(1);
        LocalDate lastDay = lastMonth.atEndOfMonth();
        List<Spot> lastMonthSpots = spotRepository.findAllByDateBetween(firstDay, lastDay);

        for (Spot spot : lastMonthSpots) {
            List<SpotCategorySummary> summaries = spotCategorySummaryRepository.findAllBySpotIdOrderByVisitCountDesc(spot.getId());
            if (summaries.isEmpty()) {
                log.info("'{}' 스팟은 지난달 방문 집계가 없어 리워드를 건너뜁니다.", spot.getPlace().getTitle());
                continue;
            }

            Map<Type, Integer> categoryRanks = createRankMapFrom(summaries);

            List<Member> visitors = visitLogRepository.findMembersBySpotId(spot.getId());

            for (Member visitor : visitors) {
                grantBadgeForVisit(visitor, spot, categoryRanks);
            }
        }
    }


    private Map<Type, Integer> createRankMapFrom(List<SpotCategorySummary> summaries) {
        Map<Type, Integer> categoryRanksMap = new LinkedHashMap<>();
        AtomicInteger rankCounter = new AtomicInteger(1);
        summaries.forEach(summary -> {
            categoryRanksMap.put(summary.getCategory().getType(), rankCounter.getAndIncrement());
        });
        return categoryRanksMap;
    }

    private void grantBadgeForVisit(Member visitor, Spot spot, Map<Type, Integer> categoryRanks) {
        if (visitor.getCategory() == null) return;

        Type visitorCategory = visitor.getCategory().getType();
        int rank = categoryRanks.getOrDefault(visitorCategory, 99);

        String badgeRank;
        if (rank == 1) badgeRank = "GOLD";
        else if (rank == 2) badgeRank = "SILVER";
        else if (rank == 3) badgeRank = "BRONZE";
        else {
            badgeRank = "NONE";
        };

        if (badgeRepository.existsByMemberAndSpot(visitor, spot)) {
            log.info("{}님은 '{}' 스팟의 뱃지를 이미 보유하고 있습니다.", visitor.getName(), spot.getPlace().getTitle());
            return;
        }

        Badge newBadge = Badge.builder()
                .rank(badgeRank)
                .member(visitor)
                .spot(spot)
                .build();
        badgeRepository.save(newBadge);

        visitor.setBadgeCount(visitor.getBadgeCount() + 1);

        log.info("{}님에게 '{}' 스팟 방문에 대한 {} 뱃지를 부여했습니다.", visitor.getName(), spot.getPlace().getTitle(), badgeRank);
    }

    /**
     * 1. 이달의 여행지 선정 및 SPOT 테이블에 등록
     */
    public void selectAndCreateSpotsFor(LocalDate today) {
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);
        if (spotRepository.existsByDate(firstDayOfMonth)) {
            log.info("{}년 {}월의 여행지는 이미 존재하므로 생성을 건너뜁니다.", today.getYear(), today.getMonthValue());
            return; // 메소드를 즉시 종료
        }
        List<Long> excludedPlaceIds = spotRepository.findAllPlaceIds();

        List<Place> jejuPicks = pickRandomPlaces(JEJU_SI_CODE, 2, excludedPlaceIds);
        List<Place> seogwipoPicks = pickRandomPlaces(SEOGWIPO_SI_CODE, 2, excludedPlaceIds);

        List<Spot> newSpots = Stream.concat(jejuPicks.stream(), seogwipoPicks.stream())
                .map(place -> new Spot(firstDayOfMonth, place))
                .collect(Collectors.toList());

        spotRepository.saveAll(newSpots);
        log.info("{}년 {}월의 여행지 {}곳을 SPOT에 등록했습니다.", today.getYear(), today.getMonthValue(), newSpots.size());
    }

    private List<Place> pickRandomPlaces(int sigunguCode, int count, List<Long> excludedIds) {
        List<Place> candidates = placeRepository.findBySigunguCodeAndIdNotIn(sigunguCode, excludedIds.isEmpty() ? null : excludedIds);
        if (candidates.size() < count) {
            log.warn("{} 지역에 추천할 장소가 부족합니다. (요청: {}, 후보: {})", sigunguCode, count, candidates.size());
            return new ArrayList<>();
        }
        Collections.shuffle(candidates);
        return candidates.subList(0, count);
    }
}