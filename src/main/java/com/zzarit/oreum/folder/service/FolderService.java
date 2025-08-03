package com.zzarit.oreum.folder.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.folder.domain.repository.FolderPlaceRepository;
import com.zzarit.oreum.folder.domain.repository.FolderRepository;
import com.zzarit.oreum.folder.service.dto.*;
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
public class FolderService {

    private final FolderRepository folderRepository;

    public void createFolder(FolderNameRequestDto request, Member member) {
        Folder folder = new Folder();
        folder.setName(request.name());
        folder.setMember(member);

        folderRepository.save(folder);
    };

    public List<FolderResponseDto> getMyFolders(Member member) {
        List<Folder> folders = folderRepository.findAllByMemberWithFetch(member);

        return folders.stream().map(FolderResponseDto::from).toList();
    }

    public void updateFolderName(Long folderId, FolderNameRequestDto request, Member member) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException("폴더"));

        if (!folder.getMember().getId().equals(member.getId())) {
            throw new UnauthorizedException("접근 권한이 없습니다.");
        }

        folder.setName(request.name());

        folderRepository.save(folder);
    }

    public void deleteFolder(Long folderId, Member member) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NotFoundException("폴더"));

        if (!folder.getMember().getId().equals(member.getId())) {
            throw new UnauthorizedException("접근 권한이 없습니다.");
        }

        folderRepository.deleteById(folderId);
    };

    public void deleteMultipleFolders(FolderIdListRequestDto request, Member member) {
        for (Long folderId : request.folderIds()) {
            deleteFolder(folderId, member);
        }
    }

    @Transactional
    public void deleteAllFolders(Member member) {
        folderRepository.deleteAllByMember(member);
    };
}
