package com.zzarit.oreum.folder.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.repository.FolderRepository;
import com.zzarit.oreum.folder.service.dto.FolderResponseDto;
import com.zzarit.oreum.folder.service.dto.FolderIdListDto;
import com.zzarit.oreum.folder.service.dto.FolderNameDto;
import com.zzarit.oreum.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public void createFolder(FolderNameDto request, Member member) {
        Folder folder = new Folder();
        folder.setName(request.name());
        folder.setMember(member);

        folderRepository.save(folder);
    };

    public List<FolderResponseDto> getMyFolders(Member member) {
        List<Folder> folders = folderRepository.findAllByMember(member);

        return folders.stream().map(folder -> new FolderResponseDto(folder.getId(), folder.getName())).toList();
    }

    public void updateFolderName(Long folderId, FolderNameDto request, Member member) {
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

    public void deleteMultipleFolders(FolderIdListDto request, Member member) {
        for (Long folderId : request.folderIds()) {
            deleteFolder(folderId, member);
        }
    }

    public void deleteAllFolders(Member member) {
        folderRepository.deleteAllByMember(member);
    };
}
