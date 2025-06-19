package com.zzarit.oreum.planner.service.dto;

public record PlannerPlaceRequestDto(
        Long placeId,
        int day,
        int order
) {}