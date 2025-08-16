package com.zzarit.oreum.folder.domain;

import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.Place;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "folder_place",
        uniqueConstraints = @UniqueConstraint(name = "uk_folder_place", columnNames = {"folder_id", "place_id"}))
public class FolderPlace extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    private Folder folder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public static FolderPlace of(Folder folder, Place place) {
        FolderPlace fp = new FolderPlace();
        fp.folder = folder;
        fp.place = place;
        return fp;
    }

}