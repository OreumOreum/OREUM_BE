package com.oreum.zzarit.spot.domain;

import com.oreum.zzarit.global.domain.BaseTimeEntity;
import com.oreum.zzarit.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "badge")
public class Badge extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rank")
    private String rank;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}