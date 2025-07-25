package com.zzarit.oreum.spot.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "spot")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "order_index")
    private Integer order;

    @OneToMany(mappedBy = "spot")
    private List<Badge> badges = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "spot")
    private List<VisitLog> visitLogs = new ArrayList<>();

    public Spot(LocalDate date, Place place,int order) {
        this.date = date;
        this.place = place;
        this.order = order;
    }
}