package com.zzarit.oreum.place.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.repository.FolderPlaceRepository;
import com.zzarit.oreum.folder.domain.repository.FolderRepository;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.ContentType;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepositoryImpl;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.*;
import com.zzarit.oreum.spot.domain.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final PlaceRepositoryImpl placeRepositoryImpl;
    private final FolderPlaceRepository folderPlaceRepository;
    private final SpotRepository spotRepository;
    private final FolderRepository folderRepository;

    public Page<Place> getSearchPlaces(PlaceSearchConditionDto condition,
                                       Integer sigunguCode,
                                       int page,
                                       int size ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return placeRepository.searchPlaces(condition, pageable,sigunguCode);
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
    public PlacesResponseDto getPlacePagination(
            String contentTypeId,
            Integer sigungucode,
            Pageable pageable,
            Member member,
            boolean typeFilter
            ) {
        Type type = typeFilter
                ? (member.getCategory() != null ? member.getCategory().getType() : null)
                : null;
        Page<Place> placeList = placeRepository.findPlaceList(contentTypeId, sigungucode, type, pageable);

        List<PlacePaginationResponseDto> dtos = placeList.getContent()
                .stream()
                .map((place) ->{
                    boolean isSaved = folderPlaceRepository.existsByMemberAndPlace(member,place);
                    return PlacePaginationResponseDto.from(place,isSaved);
                })
                .toList();

        return new PlacesResponseDto(dtos, placeList.isLast());
    }


    @Transactional
    public ReviewPaginationResponseDto getReviewPaginationByPlace(long placeId, int page, int size) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new NotFoundException("장소"));
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Review> reviews = reviewRepository.findByPlace(place, pageable);

        List<ReviewResponseDto> dtos = reviews.getContent().stream()
                .map(ReviewResponseDto::from)
                .toList();

        return new ReviewPaginationResponseDto(dtos, reviews.getTotalElements(), reviews.isLast());
    }

    @Transactional
    public PlaceDetailResponseDto getPlaceDetail(long placeId, Member member) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new NotFoundException("장소"));
        RateSummary rateSummary = reviewRepository.getRateSummaryByPlaceId(place.getId());

        boolean isSaved = folderPlaceRepository.existsByMemberAndPlace(member,place);

        LocalDate now = LocalDate.now();
        boolean isSpot = spotRepository.existsThisMonthWithPlace(now.getYear(), now.getMonth().getValue(), place);

        return PlaceDetailResponseDto.from(place, rateSummary, isSpot, isSaved);

    }

    public List<MyReviewResponseDto> getMyReviews(Member member){
        List<Review> reviews = reviewRepository.findReviewsByMember(member);
        return reviews.stream().map((review)->
                new MyReviewResponseDto
                        (review.getRate(),review.getContent(),review.getCreatedAt(),review.getUpdatedAt()))
                .toList();
    }

    public List<CategoryRecommendResponseDto> getCategoryRecommend(Member member){
        return ContentType.codes().stream()
                .map(code -> placeRepository.findOneRandomByCategoryAndContentType(
                        member.getCategory(), code))
                .flatMap(Optional::stream)
                .map(place -> new CategoryRecommendResponseDto(
                        place.getContentTypeId(),
                        place.getOriginImage()))
                .collect(Collectors.toList());
    }


}
