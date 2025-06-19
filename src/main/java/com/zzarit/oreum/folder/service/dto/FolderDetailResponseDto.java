package com.zzarit.oreum.folder.service.dto;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.place.domain.Place;

public record FolderDetailResponseDto(Long folderPlaceId, Folder folder, Place place) {
}
