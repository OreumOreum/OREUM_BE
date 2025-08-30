package com.zzarit.oreum.folder.service.dto;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;

import java.util.*;
import java.util.stream.Collectors;

public record FolderResponseDto(Long folderId, String folderName, List<String> originImages, boolean isDefault) {


    public static FolderResponseDto from(Folder folder) {
        // null-safe 하게 폴더 장소 리스트 가져오기
        List<FolderPlace> places = Optional.ofNullable(folder.getFolderPlaces())
                .orElse(Collections.emptyList());

        // 최대 4장의 이미지 URL을 추출
        List<String> images = Optional.ofNullable(extractImageUrls(places, folder.isDefault(), 4))
                .filter(imgs -> imgs.stream().anyMatch(Objects::nonNull))
                .orElse(null);


        return new FolderResponseDto(
                folder.getId(),
                folder.getName(),
                images,
                folder.isDefault()
        );
    }

    private static List<String> extractImageUrls(List<FolderPlace> folderPlaces, boolean isDefault, int limit) {

        List<String> validImages = folderPlaces.stream()
                .filter(fp -> fp.getPlace() != null) // place null 체크
                .map(fp -> fp.getPlace().getOriginImage())
                .filter(Objects::nonNull) // originImage null 체크
                .collect(Collectors.toList());

        if (validImages.isEmpty()) {
            return null;
        }

        // 전체 개수가 limit 미만일 땐, 기본 폴더면 마지막 한 장, 아니면 첫 장만
        if (validImages.size() < limit) {
            String url = isDefault ?
                    validImages.get(validImages.size() - 1) :
                    validImages.get(0);
            return Collections.singletonList(url);
        }

        // 4장 이상일 땐, 기본 폴더면 뒤에서 4장, 아니면 앞에서 4장
        int start = isDefault ? validImages.size() - limit : 0;
        int end   = isDefault ? validImages.size()         : limit;

        return validImages.subList(start, end);
    }
}
