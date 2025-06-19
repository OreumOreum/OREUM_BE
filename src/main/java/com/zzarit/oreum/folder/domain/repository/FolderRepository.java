package com.zzarit.oreum.folder.domain.repository;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    void deleteAllByMember(Member member);
}