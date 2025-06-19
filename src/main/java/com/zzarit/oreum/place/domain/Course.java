package com.zzarit.oreum.place.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
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

    @Comment("대표이미지(원본)")
    @Column(name = "origin_image")
    private String originImage;

    @Comment("대표이미지(썸네일)")
    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Comment("법정동 시군구코드 - 제주도시(110)/서귀포시(130)")
    @Column(name = "city_code")
    private String cityCode;


    @OneToMany(mappedBy = "course")
    private List<CoursePlace> coursePlaces = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Rating> ratings = new ArrayList<>();

}