package com.zzarit.oreum.spot.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "spot_category_summary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotCategorySummary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private Spot spot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_type", nullable = false)
    private Category category;

    @Column(nullable = false)
    private long visitCount = 0;
    public SpotCategorySummary(Spot spot, Category category) {
        this.spot = spot;
        this.category = category;
    }

}