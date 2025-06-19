package com.oreum.zzarit.member.domain;

import com.oreum.zzarit.global.domain.BaseTimeEntity;
import com.oreum.zzarit.place.domain.PlaceCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @OneToMany(mappedBy = "category")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<PlaceCategory> placeCategories = new ArrayList<>();

}