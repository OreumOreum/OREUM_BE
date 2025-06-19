package com.zzarit.oreum.folder.controller;

import com.zzarit.oreum.folder.service.FolderService;
import com.zzarit.oreum.folder.service.dto.FolderIdListDto;
import com.zzarit.oreum.folder.service.dto.FolderNameDto;
import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/folder")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/create-folder")
    public ResponseEntity<Void> createFolder(@RequestBody FolderNameDto request, @Parameter(hidden = true) Member member) {
        folderService.createFolder(request, member);

        return ResponseEntity.noContent().build();
    };

    @PatchMapping("/update-folder/{folderId}")
    public ResponseEntity<Void> updateFolder(@PathVariable Long folderId, @RequestBody FolderNameDto request, @Parameter(hidden = true) Member member) {
        folderService.updateFolderName(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-folder/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId, @Parameter(hidden = true) Member member) {
        folderService.deleteFolder(folderId, member);

        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("/delete-multiple-folders")
    public ResponseEntity<Void> deleteMultipleFolders(@RequestBody FolderIdListDto request, @Parameter(hidden = true) Member member) {
        folderService.deleteMultipleFolders(request, member);

        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("delete-all-folders")
    @Transactional
    public ResponseEntity<Void> deleteAllFolder(@Parameter(hidden = true) Member member) {
        folderService.deleteAllFolders(member);

        return ResponseEntity.noContent().build();
    };
}
