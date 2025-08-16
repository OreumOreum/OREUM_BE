package com.zzarit.oreum.folder.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FolderPlaceRepository extends JpaRepository<FolderPlace, Long> {
    Optional<FolderPlace> findByFolderAndPlace(Folder folder, Place place);

    List<FolderPlace> findAllByFolder(Folder folder);

    boolean existsByFolderAndPlace(Folder folder, Place place);
    void deleteByFolderAndPlace(Folder folder, Place place);

    @Query("""
            SELECT CASE WHEN COUNT(fp) > 0 THEN true ELSE false END
            FROM FolderPlace fp
            WHERE fp.folder.member = :member AND fp.place = :place
          """)
    boolean existsByMemberAndPlace(@Param("member") Member member,
                                               @Param("place") Place place);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM FolderPlace fp WHERE fp.folder.id = :folderId AND fp.place.id = :placeId")
    int deleteByFolderIdAndPlaceId(@Param("folderId") Long folderId, @Param("placeId") Long placeId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
           DELETE FROM FolderPlace fp
           WHERE fp.folder.member.id = :memberId
             AND fp.place.id = :placeId
           """)
    int deleteByMemberIdAndPlaceId(@Param("memberId") Long memberId, @Param("placeId") Long placeId);

    @Modifying
    @Query(value = """
      insert ignore into folder_place (folder_id, place_id, created_at, updated_at)
      values (:folderId, :placeId, now(), now())
      """, nativeQuery = true)
    int upsertIfAbsent(@Param("folderId") Long folderId, @Param("placeId") Long placeId);

}