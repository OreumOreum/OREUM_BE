package com.zzarit.oreum.spot.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "badge")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rank")
    private String rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}