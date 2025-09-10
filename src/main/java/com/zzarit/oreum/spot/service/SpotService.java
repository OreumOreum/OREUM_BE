package com.zzarit.oreum.spot.service;

import com.zzarit.oreum.auth.domain.repository.RefreshTokenRepository;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.SpotCategorySummary;
import com.zzarit.oreum.spot.domain.VisitLog;
import com.zzarit.oreum.spot.domain.repository.SpotCategorySummaryRepository;
import com.zzarit.oreum.spot.domain.repository.SpotRepository;
import com.zzarit.oreum.spot.domain.repository.VisitLogRepository;
import com.zzarit.oreum.spot.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 이달의 여행지 및 방문 인증 서비스 클래스
 * 
 * 매달 선정되는 이달의 여행지 조회 및 사용자의 방문 인증 기능을 제공합니다.
 * 사용자는 지정된 여행지를 방문하여 스탬프를 얻을 수 있으며,
 * 카테고리별 방문 통계 및 랭킹 기능도 지원합니다.
 */
@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final VisitLogRepository visitLogRepository;
    private final SpotCategorySummaryRepository summaryRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public List<MonthlySpotResponseDto> getCurrentMonthlySpots(Member member) {
        Category category = member.getCategory();
        LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);

        List<Spot> monthlySpots = spotRepository.findAllByDateWithPlace(firstDayOfCurrentMonth);

        return monthlySpots.stream()
                .map(spot -> {
                    SpotCategorySummary summary = summaryRepository.findBySpotAndCategory(spot, category).orElse(null);
                    Long count = (summary==null) ? 0 : summary.getVisitCount();
                    return MonthlySpotResponseDto.from(spot,count);
                })
                .toList();

    }
    @Transactional
    public void createVisitLog(Member member, Long spotId) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new IllegalArgumentException("해당 스팟을 찾을 수 없습니다. id=" + spotId));

        boolean alreadyVisited = visitLogRepository.existsByMemberAndSpot(member, spot);
        if (alreadyVisited) {
            throw new IllegalStateException("이미 방문한 스팟입니다.");
        }

        visitLogRepository.save(new VisitLog(member, spot));

        updateCategorySummary(member, spot);
    }

    private void updateCategorySummary(Member member, Spot spot) {
        Category category = member.getCategory();

        SpotCategorySummary summary = summaryRepository.findBySpotAndCategory(spot, category)
                .orElseGet(() -> new SpotCategorySummary(spot, category));

        summary.setVisitCount(summary.getVisitCount() + 1);

        summaryRepository.save(summary);
    }

    public List<RankResponseDto> getRealTimeRankForSpot(Long spotId) {
        List<SpotCategorySummary> summaries = summaryRepository.findAllBySpotIdOrderByVisitCountDesc(spotId);

        AtomicInteger rank = new AtomicInteger(1);
        return summaries.stream()
                .map(summary -> new RankResponseDto(
                        rank.getAndIncrement(),
                        summary.getCategory().getType().name(),
                        summary.getVisitCount()
                ))
                .collect(Collectors.toList());
    }

    public List<StampReponseDto> getStampList(Member member,int year){
        List<Spot> visitedSpot = spotRepository.findVisitedSpotsByMemberAndYear(member, year);
        return visitedSpot.stream()
                .map(StampReponseDto::from)
                .toList();
    }

    @Transactional
    public List<SpotPlaceResponseDto> getSpotList(Member member,int year,int month){
        LocalDate searchDate = LocalDate.of(year, month, 1);
        List<Spot> monthlySpots = spotRepository.findAllByDateWithPlace(searchDate);
        Set<Long> visitedIds = visitLogRepository.findVisitedSpotIdsByMember(member);

        return monthlySpots.stream()
                .map((spot) ->{
                    boolean visited = visitedIds.contains(spot.getId());
                    return SpotPlaceResponseDto.from(spot,visited);
                })
                .toList();
    }

    public TotalStampResponseDto getTotalStampList(Long memberId){
        return new TotalStampResponseDto(visitLogRepository.findTotalStampNumber(memberId));
    }


}