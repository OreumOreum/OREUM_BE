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
        List<String> images = extractImageUrls(places, folder.isDefault(), 4);

        return new FolderResponseDto(
                folder.getId(),
                folder.getName(),
                images,
                folder.isDefault()
        );
    }

    private static List<String> extractImageUrls(List<FolderPlace> places, boolean isDefault, int limit) {
        boolean allNull = places.stream().allMatch(Objects::isNull);

        if (places.isEmpty() || allNull) {
            return null;
        }


        // 전체 개수가 limit 미만일 땐, 기본 폴더면 마지막 한 장, 아니면 첫 장만
        if (places.size() < limit) {
            FolderPlace fp = isDefault ?
                    places.get(places.size() - 1) :
                    places.get(0);
            return Collections.singletonList(fp.getPlace().getOriginImage());
        }

        // 4장 이상일 땐, 기본 폴더면 뒤에서 4장, 아니면 앞에서 4장
        int start = isDefault ? places.size() - limit : 0;
        int end   = isDefault ? places.size()         : limit;

        return places.subList(start, end).stream()
                .map(fp -> fp.getPlace().getOriginImage())
                .collect(Collectors.toList());
    }
}
