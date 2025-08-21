package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Review;

import java.time.LocalDateTime;
import java.util.Optional;

public record ReviewResponseDto(
        long reviewId,
        Double rate,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Type type,
        boolean isMyReview
) {

    public static ReviewResponseDto from(Review review, boolean isMyReview){
        Type categoryType = Optional.ofNullable(review.getMember())
                .map(Member::getCategory)
                .map(Category::getType)
                .orElse(null);

        return new ReviewResponseDto(
                        review.getId(),
                        review.getRate(),
                        review.getContent(),
                        review.getCreatedAt(),
                        review.getUpdatedAt(),
                        categoryType,
                        isMyReview
        );
    }
}
