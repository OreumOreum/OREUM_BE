package com.oreum.zzarit.member.domain;

import com.oreum.zzarit.folder.domain.Folder;
import com.oreum.zzarit.global.domain.BaseTimeEntity;
import com.oreum.zzarit.place.domain.Rating;
import com.oreum.zzarit.place.domain.Review;
import com.oreum.zzarit.planner.domain.Planner;
import com.oreum.zzarit.spot.domain.Badge;
import com.oreum.zzarit.spot.domain.VisitLog;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "phone_no", unique = true)
    private String phoneNo;

    @ManyToOne
    @JoinColumn(name = "category_type")
    private Category category;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Folder> folders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Badge> badges = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Planner> planners = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<VisitLog> visitLogs = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Rating> ratings = new ArrayList<>();

}