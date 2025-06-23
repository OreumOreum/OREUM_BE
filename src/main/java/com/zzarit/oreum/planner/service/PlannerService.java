package com.zzarit.oreum.planner.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.folder.service.dto.FolderPlaceResponseDto;
import com.zzarit.oreum.global.exception.ForbiddenException;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.planner.domain.PlannerPlace;
import com.zzarit.oreum.planner.domain.repository.PlannerPlaceRepository;
import com.zzarit.oreum.planner.domain.repository.PlannerRepository;
import com.zzarit.oreum.planner.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlannerPlaceRepository plannerPlaceRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void createPlanner(PlannerCreateRequestDto request, Member member) {
        Planner planner = new Planner();
        planner.setName(request.name());
        planner.setMember(member);
        plannerRepository.save(planner);

        addPlannerPlaces(planner, request.places());
    }

    public List<PlannerResponseDto> getMyPlanners(Member member) {
        List<Planner> planners = plannerRepository.findAllByMember(member);

        return planners.stream().map(planner -> new PlannerResponseDto(planner.getId(), planner.getName())).toList();
    }

    public List<PlannerPlaceResponseDto> getMyPlannerPlaces(Long plannerId, Member member) {
        Planner planner = plannerRepository.findByIdAndMember(plannerId, member)
                .orElseThrow(() -> new SecurityException("접근 권한이 없습니다."));

        List<PlannerPlace> plannerPlaces = plannerPlaceRepository.findAllByPlanner(planner);

        return plannerPlaces.stream().map(PlannerPlaceResponseDto::new).toList();
    }

    @Transactional
    public void updatePlanner(Long plannerId, PlannerCreateRequestDto request, Member member) {
        Planner planner = plannerRepository.findById(plannerId)
                .orElseThrow(() -> new RuntimeException("플래너 없음"));

        if (!planner.getMember().getId().equals(member.getId())) {
            throw new RuntimeException("접근 권한 없음");
        }

        planner.setName(request.name());
        plannerPlaceRepository.deleteAllByPlanner(planner);

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
