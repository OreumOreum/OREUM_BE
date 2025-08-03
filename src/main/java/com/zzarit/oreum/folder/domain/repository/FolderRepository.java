package com.zzarit.oreum.folder.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    void deleteAllByMember(Member member);

    List<Folder> findAllByMember(Member member);


    Optional<Folder> findByIdAndMember(Long id, Member member);
    Optional<Folder> findByNameAndMember(String name, Member member);

    @Query("""
       SELECT f
       FROM Folder f
       WHERE f.member = :member AND f.isDefault = true
     
""")
    Optional<Folder> findDefaultFolder(@Param("member") Member member);


    @Query("""
        select f
        from Folder f
        join fetch f.folderPlaces fp
        left join fetch fp.place p
        where f.member = :member
        ORDER BY fp.createdAt ASC
        """)
    List<Folder> findAllByMemberWithFetch(
            @Param("member") Member member
    );

}