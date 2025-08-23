package com.zzarit.oreum.place.domain;

import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.planner.domain.PlannerPlace;
import com.zzarit.oreum.spot.domain.Spot;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "place")
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "sigungucode")
    private Integer sigunguCode;

    @Comment("대분류")
    @Column(name = "category_1")
    private String category1;

    @Comment("중분류")
    @Column(name = "category_2")
    private String category2;

    @Comment("소분류")
    @Column(name = "category_3")
    private String category3;

    @Comment("콘텐츠 ID")
    @Column(name = "content_id", unique = true)
    private String contentId;

    @Comment("관광타입 분류")
    @Column(name = "content_type_id")
    private String contentTypeId;

    @Comment("대표이미지(원본)")
    @Column(name = "origin_image")
    private String originImage;

    @Comment("대표이미지(썸네일)")
    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Comment("x좌표")
    @Column(name = "mapx")
    private Double mapx;

    @Comment("y좌표")
    @Column(name = "mapy")
    private Double mapy;

    @Comment("전화번호")
    @Column(name = "tel")
    private String tel;

    @Comment("관광지명")
    @Column(name = "title")
    private String title;

    @Comment("상세설명")
    @Column(
            name             = "overview",
            columnDefinition = "TEXT"
    )
    private String overview ;

    @Column(name = "orders")
    private Integer orders;

    @Builder.Default
    @OneToMany(mappedBy = "place")
    private List<PlaceCategory> placeCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place")
    private List<Spot> spots = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<FolderPlace> folderPlaces = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<PlannerPlace> plannerPlaces = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

}