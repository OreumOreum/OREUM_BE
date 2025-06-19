package com.zzarit.oreum.planner.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.planner.service.PlannerService;
import com.zzarit.oreum.planner.service.dto.PlannerCreateRequestDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
