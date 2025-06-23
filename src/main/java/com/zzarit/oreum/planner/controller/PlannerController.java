package com.zzarit.oreum.planner.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.planner.service.PlannerService;
import com.zzarit.oreum.planner.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PLANNER", description = "플래너 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/planner")
public class PlannerController {

    private final PlannerService plannerService;

    @Operation(summary = "플래너 생성 API", description = "본인의 플래너를 생성합니다.")
    @PostMapping("")
    public ResponseEntity<Void> createPlanner(@RequestBody PlannerCreateRequestDto request, Member member) {
        plannerService.createPlanner(request, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "플래너 조회 API", description = "본인의 플래너를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<List<PlannerResponseDto>> getMyPlanners(Member member) {
        List<PlannerResponseDto> planners = plannerService.getMyPlanners(member);

        return ResponseEntity.ok(planners);
    }

    @Operation(summary = "플래너 상세보기 API", description = "본인의 플래너에 추가된 장소와 일정을 조회합니다")
    @GetMapping("/{plannerId}")
    public ResponseEntity<List<PlannerPlaceResponseDto>> getMyPlannerPlaces(@PathVariable Long plannerId, Member member) {
        List<PlannerPlaceResponseDto> plannerplaces = plannerService.getMyPlannerPlaces(plannerId, member);

        return ResponseEntity.ok(plannerplaces);
    }

    @Operation(summary = "플래너 수정 API", description = "본인의 플래너를 수정합니다.")
    @PutMapping("/{plannerId}")
    public ResponseEntity<Void> updatePlanner(
            @PathVariable Long plannerId,
            @RequestBody PlannerCreateRequestDto request,
            Member member) {

        plannerService.updatePlanner(plannerId, request, member);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "플래너 단일 삭제 API", description = "본인의 플래너를 하나 삭제합니다.")
    @DeleteMapping("/{plannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable Long plannerId, Member member) {
        plannerService.deletePlanner(plannerId, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "플래너 다중 삭제 API", description = "본인의 플래너를 여러개 삭제합니다.")
    @DeleteMapping("/multiple")
    public ResponseEntity<Void> deleteMultiplePlanners(@RequestBody PlannerIdListRequestDto request, Member member) {
        plannerService.deleteMultiplePlanners(request, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "플래너 전체 삭제 API", description = "본인의 플래너를 전체 삭제합니다.")
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllPlanners(Member member) {
        plannerService.deleteAllPlanners(member);

        return ResponseEntity.noContent().build();
    }
}
