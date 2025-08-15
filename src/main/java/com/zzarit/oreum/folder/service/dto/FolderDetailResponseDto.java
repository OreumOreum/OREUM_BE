package com.zzarit.oreum.folder.service.dto;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;

import java.util.Objects;

public record FolderDetailResponseDto(
        Long folderId,
        String folderName,
        boolean isSaved
) {
    public static FolderDetailResponseDto from(Folder folder, Long placeId) {
        boolean saved =
                folder != null
                        && folder.getFolderPlaces() != null
                        && folder.getFolderPlaces().stream()
                        .map(FolderPlace::getPlace)
                        .filter(Objects::nonNull)
                        .map(p -> p.getId())
                        .anyMatch(id -> Objects.equals(id, placeId));

        return new FolderDetailResponseDto(
                folder != null ? folder.getId() : null,
                folder != null ? folder.getName() : null,
                saved
        );
    }
}
