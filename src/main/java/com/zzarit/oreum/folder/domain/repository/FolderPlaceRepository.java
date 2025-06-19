package com.zzarit.oreum.folder.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FolderPlaceRepository extends JpaRepository<FolderPlace, Long> {
    Optional<FolderPlace> findByFolderAndPlace(Folder folder, Place place);

    List<FolderPlace> findAllByFolder(Folder folder);

    boolean existsByFolderAndPlace(Folder folder, Place place);
}