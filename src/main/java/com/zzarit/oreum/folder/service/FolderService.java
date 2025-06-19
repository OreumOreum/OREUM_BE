package com.zzarit.oreum.folder.service;

import com.zzarit.oreum.folder.domain.Folder;
import com.zzarit.oreum.folder.domain.repository.FolderRepository;
import com.zzarit.oreum.folder.service.dto.FolderRequestDto;
import com.zzarit.oreum.folder.service.dto.FolderResponseDto;
import com.zzarit.oreum.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderResponseDto createFolder(FolderRequestDto request, Member member) {
        Folder folder = new Folder();
        folder.setName(request.name());
        folder.setMember(member);

        return new FolderResponseDto(folderRepository.save(folder).getId());
    };
}
