package com.zzarit.oreum.place.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.Rating;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.place.domain.repository.RatingRepository;
import com.zzarit.oreum.place.domain.repository.ReviewRepository;
import com.zzarit.oreum.place.service.dto.ReviewCreateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void name() {
    }

    @Test
    public void 리뷰별점테스트(){
        Member member = memberRepository.findById(1L).orElseThrow();
        Place place = placeRepository.findById(1L).orElseThrow();
        String content = "이야 너무 좋다";
        Double score = 4.5;

        ReviewCreateRequestDto request = new ReviewCreateRequestDto(1, score, content);

        placeService.createReviewAndRating(member,request);

        Review review = reviewRepository.findByMemberAndPlace(member,place).get();
        Rating rating = ratingRepository.findByMemberAndPlace(member,place).get();

        Assertions.assertThat(review.getContent()).isEqualTo(content);
        Assertions.assertThat(rating.getScore()).isEqualTo(score);
    }

    @Test
    public void 리뷰테스트(){
        Member member = memberRepository.findById(1L).orElseThrow();
        Place place = placeRepository.findById(1L).orElseThrow();
        String content = null;
        Double score = 4.5;

        ReviewCreateRequestDto request = new ReviewCreateRequestDto(1, score, content);

        placeService.createReviewAndRating(member,request);

        Rating rating = ratingRepository.findByMemberAndPlace(member,place).get();
        Optional<Review> review = reviewRepository.findByMemberAndPlace(member, place).toJavaUtil();


        Assertions.assertThat(rating.getScore()).isEqualTo(score);
        Assertions.assertThat(review).isEmpty();

    }

}