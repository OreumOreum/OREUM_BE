package com.zzarit.oreum.planner.service;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.planner.domain.PlannerPlace;
import com.zzarit.oreum.planner.domain.repository.PlannerPlaceRepository;
import com.zzarit.oreum.planner.domain.repository.PlannerRepository;
import com.zzarit.oreum.planner.service.dto.PlannerCreateRequestDto;
import com.zzarit.oreum.planner.service.dto.PlannerIdListRequestDto;
import com.zzarit.oreum.planner.service.dto.PlannerPlaceRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlannerPlaceRepository plannerPlaceRepository;
    private final PlaceRepository placeRepository;

    public void createPlanner(PlannerCreateRequestDto request, Member member) {
        Planner planner = new Planner();
        planner.setName(request.name());
        planner.setMember(member);
        plannerRepository.save(planner);

        addPlannerPlaces(planner, request.places());
    }

    private void addPlannerPlaces(Planner planner, List<PlannerPlaceRequestDto> placeDtos) {
        for (PlannerPlaceRequestDto placeDto : placeDtos) {
            Place place = placeRepository.findById(placeDto.placeId())
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 장소입니다: " + placeDto.placeId()));

            PlannerPlace plannerPlace = new PlannerPlace();
            plannerPlace.setPlanner(planner);
            plannerPlace.setPlace(place);
            plannerPlace.setSequenceDay(placeDto.day());
            plannerPlace.setSequenceOrder(placeDto.order());

            plannerPlaceRepository.save(plannerPlace);
        }
    }

    public void deletePlanner(Long plannerId, Member member) {
        Planner planner = plannerRepository.findById(plannerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 폴더가 존재하지 않습니다."));

        if (!planner.getMember().getId().equals(member.getId())) {
            throw new SecurityException("본인의 일정만 삭제할 수 있습니다.");
        }

        plannerRepository.deleteById(plannerId);
    }

    public void deleteMultiplePlanners(PlannerIdListRequestDto request, Member member) {
        for (Long plannerId : request.plannerIds()) {
            deletePlanner(plannerId, member);
        }
    }

    @Transactional
    public void deleteAllPlanners(Member member) {
        plannerRepository.deleteAllByMember(member);
    }
}
