package com.zzarit.oreum.folder.controller;

import com.zzarit.oreum.folder.service.FolderService;
import com.zzarit.oreum.folder.service.dto.FolderIdListRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderNameRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderPlaceRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderResponseDto;
import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/folder")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/create-folder")
    public ResponseEntity<Void> createFolder(@RequestBody FolderNameRequestDto request, @Parameter(hidden = true) Member member) {
        folderService.createFolder(request, member);

        return ResponseEntity.noContent().build();
    };

    @GetMapping("/get-my-folders")
    public ResponseEntity<List<FolderResponseDto>> getMyFolders(@Parameter(hidden = true) Member member) {
        List<FolderResponseDto> folders = folderService.getMyFolders(member);

        return ResponseEntity.ok(folders);
    };

    @PatchMapping("/update-folder/{folderId}")
    public ResponseEntity<Void> updateFolder(@PathVariable Long folderId, @RequestBody FolderNameRequestDto request, @Parameter(hidden = true) Member member) {
        folderService.updateFolderName(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-folder/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId, @Parameter(hidden = true) Member member) {
        folderService.deleteFolder(folderId, member);

        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("/delete-multiple-folders")
    public ResponseEntity<Void> deleteMultipleFolders(@RequestBody FolderIdListRequestDto request, @Parameter(hidden = true) Member member) {
        folderService.deleteMultipleFolders(request, member);

        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("delete-all-folders")
    @Transactional
    public ResponseEntity<Void> deleteAllFolder(@Parameter(hidden = true) Member member) {
        folderService.deleteAllFolders(member);

        return ResponseEntity.noContent().build();
    };

    @PostMapping("/{folderId}/add-folder-place")
    public ResponseEntity<Void> addFolderPlace(@PathVariable Long folderId, @RequestBody FolderPlaceRequestDto request, @Parameter(hidden = true) Member member) {
        folderService.addFolderPlace(folderId, request, member);

        return ResponseEntity.noContent().build();
    }
}
