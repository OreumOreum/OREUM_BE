package com.zzarit.oreum.folder.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FolderPlaceRepository extends JpaRepository<FolderPlace, Long> {
    Optional<FolderPlace> findByFolderAndPlace(Folder folder, Place place);

    List<FolderPlace> findAllByFolder(Folder folder);

    boolean existsByFolderAndPlace(Folder folder, Place place);

    @Query("""
    SELECT f FROM Folder f
    LEFT JOIN FETCH f.folderPlaces fp
    WHERE f.member.id = :memberId
""")
    List<Folder> findAllWithFolderPlacesByMember(@Param("memberId") Long memberId);
}