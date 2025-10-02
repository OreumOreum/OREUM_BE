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

/**
 * 회원 엔티티 클래스
 * 
 * 오름 플랫폼의 사용자 정보를 관리하는 도메인 엔티티입니다.
 * 회원의 기본 정보, 관심 카테고리, 관련된 폴더, 플래너, 방문 로그, 리뷰 정보를 포함합니다.
 * 
 * @see Category 회원의 관심 카테고리
 * @see Folder 회원이 소유한 저장 폴더
 * @see Planner 회원이 작성한 여행 계획
 * @see VisitLog 회원의 방문 기록
 * @see Review 회원이 작성한 리뷰
 */
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

    /**
     * 회원에게 폴더를 추가하는 메서드
     * 
     * 양방향 연관관계를 유지하기 위해 폴더의 회원 정보도 함께 설정합니다.
     * 
     * @param folder 추가할 폴더 객체
     */
    public void addFolder(Folder folder) {
        this.folders.add(folder);
        folder.setMember(this);
    }


    /**
     * 회원 생성자 (Builder 패턴)
     * 
     * 새로운 회원을 생성하며, 기본적으로 "모든 저장됨" 폴더를 자동으로 생성합니다.
     * 
     * @param loginId 소셜 로그인 ID (카카오, 구글, 애플 등에서 제공하는 고유 ID)
     */
    @Builder
    public Member(String loginId){
        this.loginId =loginId;
        this.addFolder(new Folder("모든 저장됨", this, true));
    }

}