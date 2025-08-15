package com.zzarit.oreum.planner.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.folder.service.dto.FolderPlaceResponseDto;
import com.zzarit.oreum.global.exception.ForbiddenException;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import com.zzarit.oreum.global.util.RandomUtils;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlannerPlaceRepository plannerPlaceRepository;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

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

        return planners.stream().map((planner) -> {
                    Integer maxDay = plannerPlaceRepository.findMaxDayByMember(planner.getId()).orElse(0);
                    return new PlannerResponseDto(planner.getId(), planner.getName(), maxDay);
                }
        ).toList();
    }

    @Transactional
    public List<PlannerPlaceResponseDto> getMyPlannerPlaces(Long plannerId, Member member) {
        Planner planner = plannerRepository.findByIdAndMember(plannerId, member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        List<PlannerPlace> plannerPlaces = plannerPlaceRepository.findAllByPlanner(planner);

        return plannerPlaces.stream().map(PlannerPlaceResponseDto::new).toList();
    }

    @Transactional
    public void updatePlanner(Long plannerId, PlannerCreateRequestDto request, Member member) {
        Planner planner = plannerRepository.findById(plannerId)
                .orElseThrow(() -> new NotFoundException("플래너"));

        if (!planner.getMember().getId().equals(member.getId())) {
            throw new UnauthorizedException("접근 권한이 없습니다");
        }

        planner.setName(request.name());
        plannerPlaceRepository.deleteAllByPlanner(planner);

        addPlannerPlaces(planner, request.places());
    }

    @Transactional
    public void updatePlannerName(Long plannerId, String name, Member member) {
        Planner planner = plannerRepository.findById(plannerId)
                .orElseThrow(() -> new NotFoundException("플래너"));

        if (!planner.getMember().getId().equals(member.getId())) {
            throw new UnauthorizedException("접근 권한이 없습니다");
        }

        planner.setName(name);
    }

    private void addPlannerPlaces(Planner planner, List<PlannerPlaceRequestDto> placeDtos) {
        for (PlannerPlaceRequestDto placeDto : placeDtos) {
            Place place = placeRepository.findById(placeDto.placeId())
                    .orElseThrow(() -> new NotFoundException("장소"));

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
                .orElseThrow(() -> new NotFoundException("폴더"));

        if (!planner.getMember().getId().equals(member.getId())) {
            throw new UnauthorizedException("접근 권한이 없습니다.");
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

    @Transactional
    public PlannerCourseResponseDto recommendCourse(Member member) {
        Type type = memberRepository.findTypeByMember(member.getId());
        List<Course> courses = courseRepository.findAllByCategoryType(type);

        Course course = RandomUtils.getRandomElement(courses);
        if (course == null) throw new NotFoundException("유형의 코스");

        List<Place> placeList = course.getPlaces();
        String plannerName = generatePlannerName(member.getId());

        return PlannerCourseResponseDto.of(plannerName, placeList);
    }

    @Transactional(readOnly = true)
    public String generatePlannerName(Long memberId) {
        String baseName = "오름오름 나만의 추천 여행코스 ";
        // 1) baseName으로 시작하는 모든 이름을 조회
        List<String> existing = plannerRepository.findNamesByNameStartingWith(baseName + "%", memberId);

        // 2) 하나도 없으면 baseName 그대로 리턴
        if (existing.isEmpty()) {
            return baseName;
        }

        // 3) 정규식으로 뒤 숫자만 뽑아서 최대값 계산
        Pattern p = Pattern.compile("^" + Pattern.quote(baseName) + "(\\d*)$");
        int maxSuffix = existing.stream()
                .map(name -> {
                    Matcher m = p.matcher(name);
                    if (m.matches() && !m.group(1).isEmpty()) {
                        return Integer.parseInt(m.group(1));
                    }
                    // “숫자 없이 순수 baseName” 도 suffix=0 으로 처리
                    return 0;
                })
                .max(Integer::compareTo)
                .orElse(1);

        // 4) 다음 숫자를 붙여서 리턴
        return baseName + (maxSuffix + 1);
    }
}
