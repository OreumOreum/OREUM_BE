package com.zzarit.oreum.planner.service.dto;

import com.zzarit.oreum.planner.domain.PlannerPlace;

public record PlannerPlaceResponseDto(Long plannerPlaceId, int day, int order, Long plannerId, Long placeId,
                                      String placeTitle,
                                      String placeAddress, String placeThumbnailImage) {
    public PlannerPlaceResponseDto(PlannerPlace plannerPlace) {
        this(plannerPlace.getId(), plannerPlace.getSequenceDay(), plannerPlace.getSequenceOrder(), plannerPlace.getPlanner().getId(), plannerPlace.getPlace().getId(), plannerPlace.getPlace().getTitle(), plannerPlace.getPlace().getAddress(), plannerPlace.getPlace().getThumbnailImage());
    }
}
