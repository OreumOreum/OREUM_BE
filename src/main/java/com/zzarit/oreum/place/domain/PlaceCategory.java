package com.zzarit.oreum.place.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.member.domain.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "place_category")
@NoArgsConstructor
public class PlaceCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_type")
    private Category category;

    public PlaceCategory(Place place,Category category){
        this.place = place;
        this.category = category;
    }

}