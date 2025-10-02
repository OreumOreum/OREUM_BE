package com.zzarit.oreum.folder.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 폴더 엔티티 클래스
 * 
 * 사용자가 관심 있는 장소들을 저장하고 관리하는 폴더 정보를 나타내는 도메인 엔티티입니다.
 * 각 회원은 여러 개의 폴더를 가질 수 있으며, 기본적으로 "모든 저장됨" 폴더가 자동 생성됩니다.
 * 
 * @see Member 폴더를 소유하는 회원
 * @see FolderPlace 폴더에 저장된 장소들
 */
@Getter
@Entity
@Table(name = "folder")
@NoArgsConstructor
public class Folder extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter(AccessLevel.NONE)
    @Column(name = "is_default", updatable = false)
    private boolean isDefault;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    private List<FolderPlace> folderPlaces = new ArrayList<>();

    /**
     * 폴더 생성자
     * 
     * 새로운 폴더를 생성합니다. 기본 폴더 여부를 설정할 수 있습니다.
     * 
     * @param name 폴더 이름
     * @param member 폴더 소유자
     * @param isDefault 기본 폴더 여부 (true: 기본 폴더, false: 사용자 생성 폴더)
     */
    public Folder(String name, Member member, boolean isDefault) {
        this.name = name;
        this.member = member;
        this.isDefault = isDefault;
    }
}