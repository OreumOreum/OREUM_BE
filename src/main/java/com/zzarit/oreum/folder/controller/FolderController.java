package com.zzarit.oreum.folder.controller;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.service.FolderService;
import com.zzarit.oreum.folder.service.dto.FolderRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderResponseDto;
import com.zzarit.oreum.member.domain.Member;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/folder")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/create-folder")
    public ResponseEntity<Void> createFolder(@Parameter(hidden = true) Member member, @RequestBody FolderRequestDto request) {
        folderService.createFolder(request, member);

        return ResponseEntity.noContent().build();
    };
}
