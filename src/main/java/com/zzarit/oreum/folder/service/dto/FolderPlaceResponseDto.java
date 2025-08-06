package com.zzarit.oreum.folder.service.dto;

import com.zzarit.oreum.folder.domain.FolderPlace;

public record FolderPlaceResponseDto(Long folderPlaceId, Long FolderId, Long placeId, String placeTitle,
                                     String placeAddress, String originImage, String contentTypeId) {
    public FolderPlaceResponseDto(FolderPlace folderPlace) {
        this(folderPlace.getId(),folderPlace.getFolder().getId(), folderPlace.getPlace().getId(), folderPlace.getPlace().getTitle(), folderPlace.getPlace().getAddress(), folderPlace.getPlace().getOriginImage(),
                folderPlace.getPlace().getContentTypeId());
    }
}
