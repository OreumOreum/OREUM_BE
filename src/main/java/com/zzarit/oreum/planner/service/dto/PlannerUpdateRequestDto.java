package com.zzarit.oreum.planner.service.dto;

import java.util.List;

public record PlannerUpdateRequestDto(
        String name,
        List<PlannerPlacePatchDto> places
) {}
