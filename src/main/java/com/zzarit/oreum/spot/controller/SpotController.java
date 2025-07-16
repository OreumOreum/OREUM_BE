package com.zzarit.oreum.spot.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.spot.service.SpotService; // 서비스 주입 변경
import com.zzarit.oreum.spot.service.dto.MonthlySpotResponseDto;
import com.zzarit.oreum.spot.service.dto.RankResponseDto;
import com.zzarit.oreum.spot.service.dto.StampReponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "SPOT", description = "스팟 및 방문 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spots")
public class SpotController {

    private final SpotService spotService; // SpotService 주입

    /*
    // 여기에 다른 스팟 관련 API들이 위치할 수 있습니다.
    @Operation(summary = "스팟 상세 정보 조회 API")
    @GetMapping("/{spotId}")
    public ResponseEntity<SpotDetailResponseDto> getSpotDetail(@PathVariable Long spotId) {
        // ...
    }
    */
    @Operation(summary = "이달의 여행지 목록 조회 API", description = "현재 월의 '이달의 여행지'로 선정된 스팟 목록을 조회합니다.")
    @GetMapping("/monthly")
    public ResponseEntity<List<MonthlySpotResponseDto>> getCurrentMonthlySpots() {
        List<MonthlySpotResponseDto> spots = spotService.getCurrentMonthlySpots();
        return ResponseEntity.ok(spots);
    }

    @Operation(summary = "방문 이달의 여행지 스탬프 리스트 API", description = "해당년도에 이달의 여행지 방문기록(스탬프)를 모두 조회합니다.(order: 0,1 = 제주시 / 2,3 = 서귀포시)")
    @GetMapping("/stamp/{year}")
    public ResponseEntity<List<StampReponseDto>> getStampList(Member member, @PathVariable int year) {
        return ResponseEntity.ok(spotService.getStampList(member,year));
    }

    @Operation(summary = "이달의 여행지 리스트보기 API", description = "해당 달에 이달의 여행지 리스트를 조회합니다.")
    @GetMapping("/stamp/{year}/{month}")
    public ResponseEntity<List<StampReponseDto>> getStampList(Member member, @PathVariable int year,@PathVariable int month ) {
        return ResponseEntity.ok(spotService.getStampList(member,year));
    }

    @Operation(summary = "스팟 방문 기록 생성 API", description = "사용자가 특정 스팟에 방문했음을 기록합니다.")
    @PostMapping("/{spotId}/visit")
    public ResponseEntity<Void> createVisitLog(
            @Parameter(hidden = true) Member member,
            @PathVariable Long spotId) {

        spotService.createVisitLog(member, spotId);

        return ResponseEntity.created(URI.create("/api/v1/members/me/visits")).build();
    }

    @Operation(summary = "특정 스팟의 실시간 카테고리별 랭킹 조회")
    @GetMapping("/{spotId}/ranking")
    public ResponseEntity<List<RankResponseDto>> getSpotRanking(@PathVariable Long spotId) {
        List<RankResponseDto> ranking = spotService.getRealTimeRankForSpot(spotId);
        return ResponseEntity.ok(ranking);
    }
}