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

    @PostMapping("/create-planner")
    public ResponseEntity<Void> createPlanner(@RequestBody PlannerCreateRequestDto request, @Parameter(hidden = true) Member member) {
        plannerService.createPlanner(request, member);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-my-planners")
    public ResponseEntity<List<PlannerResponseDto>> getMyPlanners(@Parameter(hidden = true) Member member) {
        List<PlannerResponseDto> planners = plannerService.getMyPlanners(member);

        return ResponseEntity.ok(planners);
    }

    @GetMapping("/get-my-planner-places/{plannerId}")
    public ResponseEntity<List<PlannerPlaceResponseDto>> getAllPlanners(@PathVariable Long plannerId, @Parameter(hidden = true) Member member) {
        List<PlannerPlaceResponseDto> plannerplaces = plannerService.getMyPlannerPlaces(plannerId, member);

        return ResponseEntity.ok(plannerplaces);
    }

    @PutMapping("/updatePlanner/{plannerId}")
    public ResponseEntity<Void> updatePlanner(
            @PathVariable Long plannerId,
            @RequestBody PlannerCreateRequestDto request,
            @Parameter(hidden = true) Member member) {

        plannerService.updatePlanner(plannerId, request, member);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-planner/{plannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable Long plannerId, @Parameter(hidden = true) Member member) {
        plannerService.deletePlanner(plannerId, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-multiple-planners")
    public ResponseEntity<Void> deleteMultiplePlanners(@RequestBody PlannerIdListRequestDto request, @Parameter(hidden = true) Member member) {
        plannerService.deleteMultiplePlanners(request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all-planners")
    public ResponseEntity<Void> deleteAllPlanners(@Parameter(hidden = true) Member member) {
        plannerService.deleteAllPlanners(member);

        return ResponseEntity.noContent().build();
    }
}
