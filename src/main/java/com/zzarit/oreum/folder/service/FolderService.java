package com.zzarit.oreum.folder.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.folder.domain.repository.FolderPlaceRepository;
import com.zzarit.oreum.folder.domain.repository.FolderRepository;
import com.zzarit.oreum.folder.service.dto.FolderPlaceRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderResponseDto;
import com.zzarit.oreum.folder.service.dto.FolderIdListRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderNameRequestDto;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final FolderPlaceRepository folderPlaceRepository;
    private final PlaceRepository placeRepository;


    public void createFolder(FolderNameRequestDto request, Member member) {
        Folder folder = new Folder();
        folder.setName(request.name());
        folder.setMember(member);

        folderRepository.save(folder);
    };

    public List<FolderResponseDto> getMyFolders(Member member) {
        List<Folder> folders = folderRepository.findAllByMember(member);

        return folders.stream().map(folder -> new FolderResponseDto(folder.getId(), folder.getName())).toList();
    }

    public void updateFolderName(Long folderId, FolderNameRequestDto request, Member member) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 폴더가 존재하지 않습니다."));

        if (!folder.getMember().getId().equals(member.getId())) {
            throw new SecurityException("본인의 폴더만 수정할 수 있습니다.");
        }

        folder.setName(request.name());

        folderRepository.save(folder);
    }

    public void deleteFolder(Long folderId, Member member) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 폴더가 존재하지 않습니다."));

        if (!folder.getMember().getId().equals(member.getId())) {
            throw new SecurityException("본인의 폴더만 삭제할 수 있습니다.");
        }

        folderRepository.deleteById(folderId);
    };

    public void deleteMultipleFolders(FolderIdListRequestDto request, Member member) {
        for (Long folderId : request.folderIds()) {
            deleteFolder(folderId, member);
        }
    }

    public void deleteAllFolders(Member member) {
        folderRepository.deleteAllByMember(member);
    };

    public void addFolderPlace(Long folderId, FolderPlaceRequestDto request, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new SecurityException("접근 권한이 없습니다."));

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new IllegalArgumentException("장소가 존재하지 않습니다."));

        FolderPlace folderPlace = new FolderPlace();
        folderPlace.setFolder(folder);
        folderPlace.setPlace(place);

        folderPlaceRepository.save(folderPlace);
    }
}
