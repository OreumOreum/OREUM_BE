package com.oreum.zzarit.place.domain;

import com.oreum.zzarit.global.domain.BaseTimeEntity;
import com.oreum.zzarit.member.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "place_category")
public class PlaceCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "category_type")
    private Category category;

}