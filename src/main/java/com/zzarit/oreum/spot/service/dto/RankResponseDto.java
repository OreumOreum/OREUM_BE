package com.zzarit.oreum.spot.service.dto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class RankResponseDto {
    private final int rank;
    private final String categoryType;
    private final long visitCount;

    public RankResponseDto(int rank, String categoryType, long visitCount) {
        this.rank = rank;
        this.categoryType = categoryType;
        this.visitCount = visitCount;
    }
}