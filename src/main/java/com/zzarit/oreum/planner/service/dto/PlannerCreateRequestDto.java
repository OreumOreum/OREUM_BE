package com.zzarit.oreum.planner.service.dto;

import java.util.List;

public record PlannerCreateRequestDto(
        String name,
        List<PlannerPlaceRequestDto> places
) {}