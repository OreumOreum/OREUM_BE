package com.zzarit.oreum.scheduler.service;

import com.zzarit.oreum.global.util.OreumStringUtils;
import com.zzarit.oreum.logging.config.LoggingFilter;
import com.zzarit.oreum.place.domain.*;
import com.zzarit.oreum.place.domain.repository.*;
import com.zzarit.oreum.scheduler.client.OpenApiClient;
import com.zzarit.oreum.scheduler.client.dto.DetailCommonDto;
import com.zzarit.oreum.scheduler.client.dto.DetailInfoDto;
import com.zzarit.oreum.scheduler.client.dto.SynctDto;
import com.zzarit.oreum.spot.domain.Spot;
import com.zzarit.oreum.spot.domain.repository.SpotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailySynchronizeService {
    private final OpenApiClient openApiClient;
    private final SpotRepository spotRepository;
    private final PlaceRepository placeRepository;
    private final CourseRepository courseRepository;
    private final CategoryMapRepository categoryMapRepository;
    private final PlaceCategoryRepository placeCategoryRepository;
    private final CourseCategoryRepository courseCategoryRepository;

    /**
     * 관광공사에서 이미 modifiedAt DESC로 받은 전체 목록을 입력으로 받음.
     * 이 중 최신 수정일자 기준 7일 윈도우에 해당하는 것만 upsert.
     */
    @Transactional
    public void syncDaily() {
        try {
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

            // contentId 중복 시 최신 modifiedtime만 유지
            Map<String, SynctDto> latestByContentId = windowDtos.stream()
                    .collect(Collectors.toMap(
                            SynctDto::getContentid,
                            Function.identity(),
                            (a, b) -> a.getModifiedtime().isAfter(b.getModifiedtime()) ? a : b
                    ));

            log.info("[SYNC] 최신 업데이트 기준 7일 이내 중복제거 데이터 개수 {}", latestByContentId.size());

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

            int created = 0, updated = 0, deleted = 0, skip = 0;

            // ===== Places =====
            for (SynctDto item : placeSyncList) {
                final String cid = item.getContentid();
                final boolean show = "1".equals(item.getShowflag());
                Place entity = placeMap.get(cid);

                // 삭제
                if (!show) {
                    if (entity != null) {
                        try {
                            log.info("[SYNC] Place 삭제: contentId={}, title={}, modifiedAt={}, syncAt={}", 
                                    entity.getContentId(), entity.getTitle(), item.getModifiedtime(), LocalDateTime.now());
                            deletePlace(entity);
                            deleted++;
                        } catch (Exception e) {
                            log.error("[SYNC] Place 삭제 실패: contentId={}, title={}, error={}", 
                                    entity.getContentId(), entity.getTitle(), e.getMessage(), e);
                        }
                    } else {
                        skip++;
                    }
                }
                // 생성
                else if (entity == null) {
                    try {
                        log.info("[SYNC] Place 생성: contentId={}, title={}, modifiedAt={}, syncAt={}", 
                                item.getContentid(), item.getTitle(), item.getModifiedtime(), LocalDateTime.now());
                        createPlace(item);
                        created++;
                    } catch (Exception e) {
                        log.error("[SYNC] Place 생성 실패: contentId={}, title={}, error={}", 
                                item.getContentid(), item.getTitle(), e.getMessage(), e);
                    }
                }
                // 수정
                else if (entity.getUpdatedAt() == null || entity.getUpdatedAt().isBefore(item.getModifiedtime())) {
                    try {
                        log.info("[SYNC] Place 수정: contentId={}, title={}, modifiedAt={}, syncAt={}", 
                                item.getContentid(), item.getTitle(), item.getModifiedtime(), LocalDateTime.now());
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
                    } catch (Exception e) {
                        log.error("[SYNC] Place 수정 실패: contentId={}, title={}, error={}", 
                                item.getContentid(), item.getTitle(), e.getMessage(), e);
                    }
                } else {
                    skip++;
                }
            }

            // ===== Courses =====
            for (SynctDto item : courseSyncList) {
                final String cid = item.getContentid();
                final boolean show = "1".equals(item.getShowflag());
                Course entity = courseMap.get(cid);

                // 삭제
                if (!show) {
                    if (entity != null) {
                        try {
                            log.info("[SYNC] Course 삭제: contentId={}, title={}, modifiedAt={}, syncAt={}", 
                                    entity.getContentId(), entity.getTitle(), item.getModifiedtime(), LocalDateTime.now());
                            deleteCourse(entity);
                            deleted++;
                        } catch (Exception e) {
                            log.error("[SYNC] Course 삭제 실패: contentId={}, title={}, error={}", 
                                    entity.getContentId(), entity.getTitle(), e.getMessage(), e);
                        }
                    } else {
                        skip++;
                    }
                }
                // 생성
                else if (entity == null) {
                    try {
                        log.info("[SYNC] Course 생성: contentId={}, title={}, modifiedAt={}, syncAt={}", 
                                item.getContentid(), item.getTitle(), item.getModifiedtime(), LocalDateTime.now());
                        createCourse(item);
                        created++;
                    } catch (Exception e) {
                        log.error("[SYNC] Course 생성 실패: contentId={}, title={}, error={}", 
                                item.getContentid(), item.getTitle(), e.getMessage(), e);
                    }
                }
                // 수정
                else if (entity.getUpdatedAt() == null || entity.getUpdatedAt().isBefore(item.getModifiedtime())) {
                    try {
                        log.info("[SYNC] Course 수정: contentId={}, title={}, modifiedAt={}, syncAt={}", 
                                item.getContentid(), item.getTitle(), item.getModifiedtime(), LocalDateTime.now());
                        entity.setTitle(item.getTitle());
                        entity.setSigunguCode(item.getSigungucode());
                        entity.setOriginImage(item.getFirstimage());
                        entity.setThumbnailImage(item.getFirstimage2());
                        entity.setCategory1(item.getCat1());
                        entity.setCategory2(item.getCat2());
                        entity.setCategory3(item.getCat3());
                        updated++;
                    } catch (Exception e) {
                        log.error("[SYNC] Course 수정 실패: contentId={}, title={}, error={}", 
                                item.getContentid(), item.getTitle(), e.getMessage(), e);
                    }
                } else {
                    skip++;
                }
            }

            log.info("[SYNC] Daily 동기화 {}건 (생성 {}, 수정 {}, 삭제 {}, 생략 {}), 기간: [{} ~ {}]",
                    latestByContentId.size(), created, updated, deleted, skip, windowStart, latestModifiedAt);
        } catch (Exception e) {
            log.error("[SYNC] Daily 동기화 전체 실패: {}", e.getMessage(), e);
        }
    }

    @Transactional
    public void createPlace(SynctDto item) {
        try {
            DetailCommonDto dto = openApiClient.getDetailCommon(item.getContentid());

            // 1. Place 생성 (overview 포함)
            Place newPlace = Place.builder()
                    .address(OreumStringUtils.removePrefix(item.getAddr1()))
                    .detailAddress(item.getAddr2())
                    .sigunguCode(item.getSigungucode())
                    .category1(item.getCat1())
                    .category2(item.getCat2())
                    .category3(item.getCat3())
                    .contentId(item.getContentid())
                    .contentTypeId(item.getContenttypeid())
                    .originImage(item.getFirstimage())
                    .thumbnailImage(item.getFirstimage2())
                    .mapx(item.getMapx())
                    .mapy(item.getMapy())
                    .tel(item.getTel())
                    .title(item.getTitle())
                    .overview(dto != null ? dto.getOverview() : null)
                    .build();

            placeRepository.save(newPlace);

            // 2. place-category 매핑
            List<CategoryMap> categories = categoryMapRepository.findByIdCategory3(newPlace.getCategory3());
            if (!categories.isEmpty()) {
                List<PlaceCategory> placeCategories = categories.stream()
                        .map(categoryMap -> new PlaceCategory(newPlace, categoryMap.getCategoryType()))
                        .toList();
                placeCategoryRepository.saveAll(placeCategories); // 배치 저장
            }
        } catch (Exception e) {
            log.error("[SYNC] DetailCommon API 호출 실패: contentId={}, error={}", item.getContentid(), e.getMessage(), e);
        }
    }

    @Transactional
    public void createCourse(SynctDto item) {
        try {
            DetailCommonDto commonDto = openApiClient.getDetailCommon(item.getContentid());
            List<DetailInfoDto> dtos = openApiClient.getDetailInfo(item.getContentid());

            // 1. Course 생성
            Course newCourse = Course.builder()
                    .title(item.getTitle())
                    .contentId(item.getContentid())
                    .sigunguCode(item.getSigungucode())
                    .originImage(item.getFirstimage())
                    .thumbnailImage(item.getFirstimage2())
                    .category1(item.getCat1())
                    .category2(item.getCat2())
                    .category3(item.getCat3())
                    .overview(commonDto != null ? commonDto.getOverview() : null)
                    .build();

            courseRepository.save(newCourse); // 영속화

            // 2. course-category 매핑
            List<CategoryMap> categories = categoryMapRepository.findByIdCategory3(newCourse.getCategory3());
            List<CourseCategory> courseCategories = categories.stream()
                    .map(categoryMap -> new CourseCategory(newCourse, categoryMap.getCategoryType()))
                    .toList();
            courseCategoryRepository.saveAll(courseCategories);

            // 3. course-place 매핑
            if (dtos != null && !dtos.isEmpty()) {
                List<String> contentIds = dtos.stream()
                        .map(DetailInfoDto::getContentId)
                        .toList();
                List<Place> places = placeRepository.findAllByContentIdIn(contentIds);
                Map<String, DetailInfoDto> dtoMap = dtos.stream()
                        .collect(Collectors.toMap(DetailInfoDto::getContentId, Function.identity()));

                places.forEach(place -> {
                    DetailInfoDto dto = dtoMap.get(place.getContentId());
                    if (dto != null) {
                        place.setCourse(newCourse);
                        place.setOrders(dto.getOrder());
                    }
                });
            }
        } catch (Exception e) {
            log.error("[SYNC] Course 생성 중 API 호출 실패: contentId={}, error={}", item.getContentid(), e.getMessage(), e);
        }
    }

    /**
     * 제약조건 safe하게 삭제
     */
    @Transactional
    public void deletePlace(Place place) {
        List<Spot> spots = place.getSpots();
        spots.forEach(spot -> spot.setPlace(null));

        spotRepository.saveAll(spots);
        placeRepository.delete(place);
    }

    @Transactional
    public void deleteCourse(Course course) {
        List<Place> places = course.getPlaces();
        places.forEach(p -> p.setCourse(null));

        placeRepository.saveAll(places);
        courseRepository.delete(course);
    }
}