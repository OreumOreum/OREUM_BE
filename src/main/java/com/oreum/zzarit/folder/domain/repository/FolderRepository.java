package com.oreum.zzarit.folder.domain.repository;

import com.oreum.zzarit.folder.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}