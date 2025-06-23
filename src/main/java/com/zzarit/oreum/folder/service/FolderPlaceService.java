package com.zzarit.oreum.folder.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.folder.domain.repository.FolderPlaceRepository;
import com.zzarit.oreum.folder.domain.repository.FolderRepository;
import com.zzarit.oreum.folder.service.dto.FolderPlaceListRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderPlaceRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderPlaceResponseDto;
import com.zzarit.oreum.folder.service.dto.FolderResponseDto;
import com.zzarit.oreum.global.exception.BadRequestException;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FolderPlaceService {

    private final FolderRepository folderRepository;
    private final FolderPlaceRepository folderPlaceRepository;
    private final PlaceRepository placeRepository;

    public void addFolderPlace(Long folderId, FolderPlaceRequestDto request, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new NotFoundException("장소"));

        boolean exists = folderPlaceRepository.existsByFolderAndPlace(folder, place);
        if (exists) {
            throw new BadRequestException("이미 추가된 장소입니다.");
        }

        FolderPlace folderPlace = new FolderPlace();
        folderPlace.setFolder(folder);
        folderPlace.setPlace(place);

        folderPlaceRepository.save(folderPlace);
    }

    public List<FolderPlaceResponseDto> getMyFolderPlaces(Long folderId, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        List<FolderPlace> folderPlaces = folderPlaceRepository.findAllByFolder(folder);

        return folderPlaces.stream().map(FolderPlaceResponseDto::new).toList();
    }

    public void deleteFolderPlace(Long folderId, FolderPlaceRequestDto request, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new NotFoundException("장소"));

        FolderPlace folderPlace = folderPlaceRepository.findByFolderAndPlace(folder, place)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        folderPlaceRepository.delete(folderPlace);
    }

    public void deleteMultipleFolderPlaces(Long folderId, FolderPlaceListRequestDto request, Member member) {

        for (Long folderPlaceId : request.placeIds()) {
            deleteFolderPlace(folderId, new FolderPlaceRequestDto(folderPlaceId), member);
        }
    }

    public void deleteAllFolderPlaces(Long folderId,  Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        List<FolderPlace> folderPlaces = folderPlaceRepository.findAllByFolder(folder);

        folderPlaceRepository.deleteAll(folderPlaces);
    }
}
