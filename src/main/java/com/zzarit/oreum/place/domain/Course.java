package com.zzarit.oreum.place.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.detail.CourseDetail;
import com.zzarit.oreum.place.service.dto.CourseDetailDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor()
@AllArgsConstructor
@Table(name = "course")
public class Course extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("코스명")
    @Column(name = "title")
    private String title;

    @Comment("컨텐츠 ID")
    @Column(name = "content_id")
    private String contentId;

    @Comment("시군구코드:3-서귀포시/4-제주시")
    @Column(name = "sigungu_code")
    private Integer sigunguCode;

    @Comment("대표이미지(원본)")
    @Column(name = "origin_image")
    private String originImage;

    @Comment("대표이미지(썸네일)")
    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Comment("대분류")
    @Column(name = "category_1")
    private String category1;

    @Comment("중분류")
    @Column(name = "category_2")
    private String category2;

    @Comment("소분류")
    @Column(name = "category_3")
    private String category3;

    @Comment("소개글")
    @Column(name = "overview")
    private String overview;

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<Place> places = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<CourseCategory> courseCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "course", fetch = FetchType.LAZY)
    private CourseDetail courseDetail;
}