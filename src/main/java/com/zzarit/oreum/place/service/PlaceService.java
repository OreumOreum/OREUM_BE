package com.zzarit.oreum.place.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepositoryImpl;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final PlaceRepositoryImpl placeRepositoryImpl;

    public Page<Place> getSearchPlaces(PlaceSearchConditionDto condition, Pageable pageable) {
        return placeRepository.searchPlaces(condition, pageable);
    }

    @Transactional
    public void createReviewAndRating(Member member, ReviewCreateRequestDto request) {
        Place place = placeRepository.findById(request.placeId()).orElseThrow(
                () -> new NotFoundException("장소")
        );

        Review review = new Review(request.content(), request.rate(), place, member);

        reviewRepository.save(review);
    }

    @Transactional
    public PlacesResponseDto getPlacePagination(Integer sigungucode,
                                                int page,
                                                int size,
                                                Member member) {
        Pageable pageable = PageRequest.of(page, size);
        Type type = (member.getCategory() != null) ? member.getCategory().getType() : null;
        Page<Place> placeList = placeRepositoryImpl.findPlaceList(sigungucode, type, pageable);
        List<PlacePaginationResponseDto> dtos = placeList.getContent()
                .stream()
                .map(PlacePaginationResponseDto::from)
                .toList();

        return new PlacesResponseDto(dtos,placeList.isLast());
    }


}
