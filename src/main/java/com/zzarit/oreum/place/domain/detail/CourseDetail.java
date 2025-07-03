package com.zzarit.oreum.place.domain.detail;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_detail")
public class CourseDetail extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id")
    private Course course;

    // 코스총거리
    @Column(name = "distance")
    private String distance;

    // 문의 및 안내
    @Column(name = "infocentertourcourse")
    private String infocentertourcourse;

    // 코스 일정
    @Column(name = "schedule")
    private String schedule;

    // 코스 총 소요 시간
    @Column(name = "taketime")
    private String taketime;

    // 코스 테마
    @Column(name = "theme")
    private String theme;
}