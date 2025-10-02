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
import com.zzarit.oreum.global.exception.ForbiddenException;
import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.global.exception.UnauthorizedException;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 폴더-장소 연결 관리 서비스 클래스
 * 
 * 폴더에 장소를 추가/삭제하는 기능을 제공합니다.
 * 기본 폴더의 특수 동작 및 중복 장소 추가 방지 로직을 포함하며,
 * 사용자가 자신의 관심 장소를 체계적으로 분류할 수 있도록 돕습니다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FolderPlaceService {

    private final FolderRepository folderRepository;
    private final FolderPlaceRepository folderPlaceRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public void addFolderPlace(Long folderId, FolderPlaceRequestDto request, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(ForbiddenException::new);

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new NotFoundException("장소"));

        Folder defaultFolder = folderRepository.findDefaultFolder(member)
                .orElseThrow(() -> new NotFoundException("기본폴더"));

        int inserted = folderPlaceRepository.upsertIfAbsent(folder.getId(), place.getId());
        if (inserted == 0) {
            throw new BadRequestException("이미 추가된 장소입니다.");
        }

        if (!defaultFolder.getId().equals(folder.getId())) {
            folderPlaceRepository.upsertIfAbsent(defaultFolder.getId(), place.getId());
        }
    }

    public void addDefaultFolderPlace(FolderPlaceRequestDto request, Member member) {
        Folder folder = folderRepository.findDefaultFolder(member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new NotFoundException("장소"));

        Optional<FolderPlace> existing = folderPlaceRepository.findByFolderAndPlace(folder, place);

        if (existing.isPresent()) {
            ///UpdateAt을 갱신해야 한다면 덮어씌우기
            return;
        }

        FolderPlace folderPlace = new FolderPlace();
        folderPlace.setFolder(folder);
        folderPlace.setPlace(place);

        folderPlaceRepository.save(folderPlace);
    }

    @Transactional
    public List<FolderPlaceResponseDto> getMyFolderPlaces(Long folderId, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(() -> new UnauthorizedException("접근 권한이 없습니다."));

        List<FolderPlace> folderPlaces = folderPlaceRepository.findAllByFolder(folder);

        return folderPlaces.stream().map(FolderPlaceResponseDto::new).toList();
    }

    @Transactional
    public void deleteFolderPlace(Long folderId, FolderPlaceRequestDto request, Member member) {
        Folder folder = folderRepository.findByIdAndMember(folderId, member)
                .orElseThrow(ForbiddenException::new);

        Long placeId = request.placeId();

        if (folder.isDefault()) {
            // 기본 폴더 삭제 정책: 해당 회원의 모든 폴더에서 해당 장소 연결 제거
            int affected = folderPlaceRepository.deleteByMemberIdAndPlaceId(member.getId(), placeId);
            if (affected == 0) {
                // 기본 폴더에도 없고 어디에도 없었던 경우
                throw new NotFoundException("폴더의 장소");
            }
            return;
        }

        // 기본 폴더가 아닌 경우: 해당 폴더-장소 연결만 제거
        int affected = folderPlaceRepository.deleteByFolderIdAndPlaceId(folderId, placeId);
        if (affected == 0) {
            throw new NotFoundException("폴더의 장소");
        }

    }

    @Transactional
    public void deleteDefaultFolderPlace(FolderPlaceRequestDto request, Member member) {
        Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new NotFoundException("장소"));

        List<Folder> folders = folderRepository.findAllByMember(member);

        for (Folder folder : folders) {
            folderPlaceRepository.deleteByFolderAndPlace(folder, place);
        }
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
