package com.zzarit.oreum.planner.service.dto;

public record PlannerPlacePatchDto(
        Long id,       // null 이면 새로 생성
        Long placeId,
        int day,
        int order
) {}
