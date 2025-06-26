package com.zzarit.oreum.place.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.Rating;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.RatingRepository;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.PlaceSearchConditionDto;
import com.zzarit.oreum.place.service.dto.ReviewCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final RatingRepository ratingRepository;
    private final ReviewRepository reviewRepository;

    public Page<Place> getSearchPlaces(PlaceSearchConditionDto condition, Pageable pageable) {
        return placeRepository.searchPlaces(condition, pageable);
    }

    @Transactional
    public void createReviewAndRating(Member member, ReviewCreateRequestDto request) {
        Place place = placeRepository.findById(request.placeId()).orElseThrow(
                () -> new NotFoundException("장소")
        );

        Rating rating = new Rating(request.score(),place,member);
        ratingRepository.save(rating);

        if (request.content() == null) {
            return;
        }

        Review review = new Review(request.content(),place,member);
        reviewRepository.save(review);
    }
}
