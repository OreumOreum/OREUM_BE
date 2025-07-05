package com.zzarit.oreum.place.service.dto;

import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Review;

import java.time.LocalDateTime;

public record ReviewResponseDto(
        Double rate,
        String content,
        LocalDateTime updatedAt,
        Type type
) {

    public ReviewResponseDto from(Review review){
        return new ReviewResponseDto(
                        review.getRate(),
                        review.getContent(),
                        review.getUpdatedAt(),
                        review.getMember().getCategory().getType()
        );
    }
}
