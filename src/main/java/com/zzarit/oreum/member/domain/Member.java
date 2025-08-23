package com.zzarit.oreum.member.domain;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Review;
import com.zzarit.oreum.planner.domain.Planner;
import com.zzarit.oreum.spot.domain.VisitLog;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "skip")
    private Boolean skip = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_type")
    private Category category;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Folder> folders = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Planner> planners = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<VisitLog> visitLogs = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    public void addFolder(Folder folder) {
        this.folders.add(folder);
        folder.setMember(this);
    }


    @Builder
    public Member(String loginId){
        this.loginId =loginId;
        this.addFolder(new Folder("모든 저장됨", this, true));
    }

}