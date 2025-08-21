package com.zzarit.oreum.scheduler.service;

import com.zzarit.oreum.global.util.OreumStringUtils;
import com.zzarit.oreum.logging.config.LoggingFilter;
import com.zzarit.oreum.place.domain.Course;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.repository.CourseRepository;
import com.zzarit.oreum.place.domain.repository.PlaceRepository;
import com.zzarit.oreum.scheduler.client.OpenApiClient;
import com.zzarit.oreum.scheduler.client.dto.SynctDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailySynchronizeService {
    private final OpenApiClient openApiClient;
    private final LoggingFilter loggingFilter;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;


    /**
     * 관광공사에서 이미 modifiedAt DESC로 받은 전체 목록을 입력으로 받음.
     * 이 중 최신 수정일자 기준 7일 윈도우에 해당하는 것만 upsert.
     */
    @Transactional
    public void syncDaily() {
        List<SynctDto> syncList = openApiClient.getAreaBasedSyncList2(300);
        if (syncList == null || syncList.isEmpty()) {
            log.info("[SYNC] 동기화 대상이 없습니다.");
            return;
        }

        LocalDateTime latestModifiedAt = syncList.get(0).getModifiedtime();
        LocalDateTime windowStart = latestModifiedAt.minusDays(7);

        // 윈도우 필터
        List<SynctDto> windowDtos = syncList.stream()
                .takeWhile(dto -> !dto.getModifiedtime().isBefore(windowStart))
                .toList();

        log.info("[SYNC] 최신 업데이트 기준 7일 이내 데이터 개수 {}", windowDtos.size());

        // contentId 중복 시 최신 modifiedtime만 유지
        Map<String, SynctDto> latestByContentId = windowDtos.stream()
                .collect(Collectors.toMap(
                        SynctDto::getContentid,
                        Function.identity(),
                        (a, b) -> a.getModifiedtime().isAfter(b.getModifiedtime()) ? a : b
                ));

        // 타입 분리 (contenttypeid == "25" => Course)
        List<SynctDto> placeSyncList = latestByContentId.values().stream()
                .filter(dto -> !"25".equals(dto.getContenttypeid()))
                .toList();
        List<SynctDto> courseSyncList = latestByContentId.values().stream()
                .filter(dto -> "25".equals(dto.getContenttypeid()))
                .toList();

        // 일괄 조회로 N+1 방지
        Map<String, Place> placeMap = placeRepository
                .findAllByContentIdIn(placeSyncList.stream().map(SynctDto::getContentid).toList())
                .stream().collect(Collectors.toMap(Place::getContentId, Function.identity()));

        Map<String, Course> courseMap = courseRepository
                .findAllByContentIdIn(courseSyncList.stream().map(SynctDto::getContentid).toList())
                .stream().collect(Collectors.toMap(Course::getContentId, Function.identity()));

        int created = 0, updated = 0, deleted = 0;

        // ===== Places =====
        for (SynctDto item : placeSyncList) {
            final String cid = item.getContentid();
            final boolean show = "1".equals(item.getShowflag());
            Place entity = placeMap.get(cid);

            if (!show) {
                if (entity != null) { placeRepository.delete(entity); deleted++; }
                continue;
            }

            if (entity == null) {
                Place newPlace = Place.builder()
                        .address(OreumStringUtils.removePrefix(item.getAddr1()))
                        .detailAddress(item.getAddr2())
                        .sigunguCode(item.getSigungucode())
                        .category1(item.getCat1())
                        .category2(item.getCat2())
                        .category3(item.getCat3())
                        .contentId(cid)
                        .contentTypeId(item.getContenttypeid())
                        .originImage(item.getFirstimage())
                        .thumbnailImage(item.getFirstimage2())
                        .mapx(item.getMapx())
                        .mapy(item.getMapy())
                        .tel(item.getTel())
                        .title(item.getTitle())
                        .build();
                placeRepository.save(newPlace);
                created++;
                continue;
            }

            // ✅ updatedAt < modifiedtime 일 때만 갱신
            if (entity.getUpdatedAt() == null || entity.getUpdatedAt().isBefore(item.getModifiedtime())) {
                entity.setAddress(OreumStringUtils.removePrefix(item.getAddr1()));
                entity.setDetailAddress(item.getAddr2());
                entity.setSigunguCode(item.getSigungucode());
                entity.setCategory1(item.getCat1());
                entity.setCategory2(item.getCat2());
                entity.setCategory3(item.getCat3());
                entity.setContentTypeId(item.getContenttypeid());
                entity.setOriginImage(item.getFirstimage());
                entity.setThumbnailImage(item.getFirstimage2());
                entity.setMapx(item.getMapx());
                entity.setMapy(item.getMapy());
                entity.setTel(item.getTel());
                entity.setTitle(item.getTitle());
                updated++;
            }
        }

        // ===== Courses =====
        for (SynctDto item : courseSyncList) {
            final String cid = item.getContentid();
            final boolean show = "1".equals(item.getShowflag());
            Course entity = courseMap.get(cid);

            if (!show) {
                if (entity != null) { courseRepository.delete(entity); deleted++; }
                continue;
            }

            if (entity == null) {
                Course newCourse = Course.builder()
                        .title(item.getTitle())
                        .contentId(cid)
                        .sigunguCode(item.getSigungucode())
                        .originImage(item.getFirstimage())
                        .thumbnailImage(item.getFirstimage2())
                        .category1(item.getCat1())
                        .category2(item.getCat2())
                        .category3(item.getCat3())
                        .build();
                courseRepository.save(newCourse);
                created++;
                continue;
            }

            if (entity.getUpdatedAt() == null || entity.getUpdatedAt().isBefore(item.getModifiedtime())) {
                entity.setTitle(item.getTitle());
                entity.setSigunguCode(item.getSigungucode());
                entity.setOriginImage(item.getFirstimage());
                entity.setThumbnailImage(item.getFirstimage2());
                entity.setCategory1(item.getCat1());
                entity.setCategory2(item.getCat2());
                entity.setCategory3(item.getCat3());
                updated++;
            }
        }

        log.info("[SYNC] Daily 동기화 {}건 (생성 {}, 수정 {}, 삭제 {}), 기간: [{} ~ {}]",
                latestByContentId.size(), created, updated, deleted, windowStart, latestModifiedAt);
    }
}
