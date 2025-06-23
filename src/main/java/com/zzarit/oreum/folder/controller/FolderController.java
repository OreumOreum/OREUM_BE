package com.zzarit.oreum.folder.controller;

import com.zzarit.oreum.folder.service.FolderPlaceService;
import com.zzarit.oreum.folder.service.FolderService;
import com.zzarit.oreum.folder.service.dto.*;
import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FOLDER", description = "폴더 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/folder")
public class FolderController {

    private final FolderService folderService;
    private final FolderPlaceService folderPlaceService;

    @Operation(summary = "폴더 생성 API", description = "본인의 폴더를 생성합니다.")
    @PostMapping("")
    public ResponseEntity<Void> createFolder(@RequestBody FolderNameRequestDto request, Member member) {
        folderService.createFolder(request, member);

        return ResponseEntity.noContent().build();
    };

    @Operation(summary = "폴더 조회 API", description = "본인의 폴더를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<List<FolderResponseDto>> getMyFolders(Member member) {
        List<FolderResponseDto> folders = folderService.getMyFolders(member);

        return ResponseEntity.ok(folders);
    };

    @Operation(summary = "폴더 수정 API", description = "본인의 폴더 이름을 수정합니다.")
    @PatchMapping("/{folderId}")
    public ResponseEntity<Void> updateFolder(@PathVariable Long folderId, @RequestBody FolderNameRequestDto request, Member member) {
        folderService.updateFolderName(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "폴더 단일 삭제 API", description = "본인의 폴더를 하나 삭제합니다.")
    @DeleteMapping("/{folderId}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long folderId, Member member) {
        folderService.deleteFolder(folderId, member);

        return ResponseEntity.noContent().build();
    };

    @Operation(summary = "폴더 다중 삭제 API", description = "본인의 폴더를 여러개 삭제합니다.")
    @DeleteMapping("/multiple")
    public ResponseEntity<Void> deleteMultipleFolders(@RequestBody FolderIdListRequestDto request, Member member) {
        folderService.deleteMultipleFolders(request, member);

        return ResponseEntity.noContent().build();
    };

    @Operation(summary = "폴더 전체 삭제 API", description = "본인의 폴더를 전체 삭제합니다.")
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllFolder( Member member) {
        folderService.deleteAllFolders(member);

        return ResponseEntity.noContent().build();
    };

    @Operation(summary = "폴더 장소 추가 API", description = "본인의 폴더에 장소를 추가합니다.")
    @PostMapping("/{folderId}")
    public ResponseEntity<Void> addFolderPlace(@PathVariable Long folderId, @RequestBody FolderPlaceRequestDto request, Member member) {
        folderPlaceService.addFolderPlace(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "폴더 상세보기 API", description = "본인의 폴더에 추가된 장소를 조회합니다.")
    @GetMapping("/{folderId}")
    public ResponseEntity<List<FolderPlaceResponseDto>> getMyFolderPlaces(@PathVariable Long folderId, Member member) {
        List<FolderPlaceResponseDto> folderPlaces = folderPlaceService.getMyFolderPlaces(folderId, member);

        return ResponseEntity.ok(folderPlaces);
    }

    @Operation(summary = "폴더 장소 단일 삭제 API", description = "본인의 폴더에 추가된 장소를 하나 삭제합니다.")
    @DeleteMapping("/place/{folderId}")
    public ResponseEntity<Void> deleteFolderPlace(@PathVariable Long folderId, @RequestBody FolderPlaceRequestDto request, Member member) {
        folderPlaceService.deleteFolderPlace(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "폴더 장소 다중 삭제 API", description = "본인의 폴더에 추가된 장소를 여러개 삭제합니다.")
    @DeleteMapping("/multiple/{folderId}")
    public ResponseEntity<Void> deleteMultipleFolderPlaces(@PathVariable Long folderId, @RequestBody FolderPlaceListRequestDto request,  Member member) {
        folderPlaceService.deleteMultipleFolderPlaces(folderId, request, member);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "폴더 장소 전체 삭제 API", description = "본인의 폴더에 추가된 장소를 전체 삭제합니다.")
    @DeleteMapping("/all/{folderId}")
    public ResponseEntity<Void> deleteAllFolderPlaces(@PathVariable Long folderId, Member member) {
        folderPlaceService.deleteAllFolderPlaces(folderId, member);

        return ResponseEntity.noContent().build();
    }
}
