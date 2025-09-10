package com.zzarit.oreum.planner.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 플래너 엔티티 클래스
 * 
 * 사용자가 작성하는 여행 계획 정보를 관리하는 도메인 엔티티입니다.
 * 플래너에는 여러 장소들을 추가할 수 있으며, 개인의 여행 일정을 체계적으로 관리할 수 있습니다.
 * 
 * @see Member 플래너를 작성한 회원
 * @see PlannerPlace 플래너에 추가된 장소들
 */
@Getter
@Setter
@Entity
@Table(name = "planner")
public class Planner extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "planner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PlannerPlace> plannerPlaces = new ArrayList<>();

}