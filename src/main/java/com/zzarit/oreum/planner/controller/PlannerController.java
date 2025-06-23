package com.zzarit.oreum.planner.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.planner.service.PlannerService;
import com.zzarit.oreum.planner.service.dto.*;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/planner")
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("")
    public ResponseEntity<Void> createPlanner(@RequestBody PlannerCreateRequestDto request, Member member) {
        plannerService.createPlanner(request, member);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<PlannerResponseDto>> getMyPlanners(Member member) {
        List<PlannerResponseDto> planners = plannerService.getMyPlanners(member);

        return ResponseEntity.ok(planners);
    }

    @GetMapping("/{plannerId}")
    public ResponseEntity<List<PlannerPlaceResponseDto>> getAllPlanners(@PathVariable Long plannerId, Member member) {
        List<PlannerPlaceResponseDto> plannerplaces = plannerService.getMyPlannerPlaces(plannerId, member);

        return ResponseEntity.ok(plannerplaces);
    }

    @PutMapping("/{plannerId}")
    public ResponseEntity<Void> updatePlanner(
            @PathVariable Long plannerId,
            @RequestBody PlannerCreateRequestDto request,
            Member member) {

        plannerService.updatePlanner(plannerId, request, member);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{plannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable Long plannerId, Member member) {
        plannerService.deletePlanner(plannerId, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/multiple")
    public ResponseEntity<Void> deleteMultiplePlanners(@RequestBody PlannerIdListRequestDto request, Member member) {
        plannerService.deleteMultiplePlanners(request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllPlanners(Member member) {
        plannerService.deleteAllPlanners(member);

        return ResponseEntity.noContent().build();
    }
}
