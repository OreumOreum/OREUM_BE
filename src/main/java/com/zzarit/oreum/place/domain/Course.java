package com.zzarit.oreum.place.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * 여행 코스 엔티티 클래스
 * 
 * 제주도의 테마별 여행 코스 정보를 관리하는 도메인 엔티티입니다.
 * 공공데이터포털의 관광코스 API를 통해 수집된 정보를 저장하며,
 * 코스에 포함된 여러 장소들을 순서대로 관리합니다.
 * 
 * @see Place 코스에 포함된 장소들
 * @see CourseCategory 코스의 카테고리 분류
 * @see Review 코스에 대한 리뷰
 */
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
    @Lob
    @Column(
            name             = "overview",
            columnDefinition = "TEXT"     // ← MySQL TEXT 타입으로 강제
    )
    private String overview;

    @Builder.Default
    @OneToMany(mappedBy = "course")
    @OrderBy("orders")
    private List<Place> places = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<CourseCategory> courseCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();
}

