package com.zzarit.oreum.folder.controller;

import com.zzarit.oreum.folder.service.FolderPlaceService;
import com.zzarit.oreum.folder.service.FolderService;
import com.zzarit.oreum.folder.service.dto.*;
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
    private final FolderPlaceService folderPlaceService;

    @PostMapping("")
    public ResponseEntity<Void> createFolder(@RequestBody FolderNameRequestDto request, Member member) {
        folderService.createFolder(request, member);

        return ResponseEntity.noContent().build();
    };

    @GetMapping("")
    public ResponseEntity<List<FolderResponseDto>> getMyFolders(Member member) {
        List<FolderResponseDto> folders = folderService.getMyFolders(member);

        return ResponseEntity.ok(folders);
    };

    @PatchMapping("/{folderId}")
    public ResponseEntity<Void> updateFolder(@PathVariable Long folderId, @RequestBody FolderNameRequestDto request, Member member) {
        folderService.updateFolderName(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId, Member member) {
        folderService.deleteFolder(folderId, member);

        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("/multiple")
    public ResponseEntity<Void> deleteMultipleFolders(@RequestBody FolderIdListRequestDto request, Member member) {
        folderService.deleteMultipleFolders(request, member);

        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllFolder( Member member) {
        folderService.deleteAllFolders(member);

        return ResponseEntity.noContent().build();
    };

    @PostMapping("/{folderId}")
    public ResponseEntity<Void> addFolderPlace(@PathVariable Long folderId, @RequestBody FolderPlaceRequestDto request, Member member) {
        folderPlaceService.addFolderPlace(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{folderId}")
    public ResponseEntity<List<FolderPlaceResponseDto>> getMyFolderPlaces(@PathVariable Long folderId, Member member) {
        List<FolderPlaceResponseDto> folderPlaces = folderPlaceService.getMyFolderPlaces(folderId, member);

        return ResponseEntity.ok(folderPlaces);
    }

    @DeleteMapping("/place/{folderId}")
    public ResponseEntity<Void> deleteFolderPlace(@PathVariable Long folderId, @RequestBody FolderPlaceRequestDto request, Member member) {
        folderPlaceService.deleteFolderPlace(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/multiple/{folderId}")
    public ResponseEntity<Void> deleteMultipleFolderPlaces(@PathVariable Long folderId, @RequestBody FolderPlaceListRequestDto request,  Member member) {
        folderPlaceService.deleteMultipleFolderPlaces(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all/{folderId}")
    public ResponseEntity<Void> deleteAllFolderPlaces(@PathVariable Long folderId, Member member) {
        folderPlaceService.deleteAllFolderPlaces(folderId, member);

        return ResponseEntity.noContent().build();
    }
}
