package com.zzarit.oreum.place.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.QCategory;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.QPlace;
import com.zzarit.oreum.place.domain.QPlaceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 장소 데이터 접근 레포지터리 인터페이스
 * 
 * 장소 엔티티에 대한 기본적인 CRUD 연산과 사용자 정의 쿼리를 제공합니다.
 * 지역별 장소 조회, 카테고리별 추천 장소 조회, 랜덤 장소 선택 등의 기능을 포함합니다.
 * PlaceRepositoryCustom 인터페이스를 확장하여 복잡한 동적 쿼리도 지원합니다.
 * 
 * @see PlaceRepositoryCustom 복잡한 검색 쿼리를 위한 사용자 정의 인터페이스
 */
public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
    List<Place> findBySigunguCode(Integer sigunguCode);
    List<Place> findBySigunguCodeAndIdNotIn(Integer sigunguCode, List<Long> ids);
    Page<Place> findPlaceList(String contentTypeId, Integer sigunguCode, Type type,Pageable pageable);

    @Query("select p.contentId from Place p where p.contentId in :ids")
    List<String> findAllContentIdIn(@Param("ids") Collection<String> ids);

    Optional<Place> findByContentId(String contentId);

    @Query("""
        SELECT p
        FROM Place p
        JOIN p.placeCategories pc
        WHERE pc.category      = :category
          AND p.contentTypeId  = :contentTypeId
          AND p.originImage IS NOT NULL
        ORDER BY function('RAND')
        """)
    List<Place> findFirstRandomByCategoryAndContentType(
            @Param("category") Category category,
            @Param("contentTypeId") String contentTypeId,
            Pageable pageable
    );

    default Optional<Place> findOneRandomByCategoryAndContentType(
            Category category,
            String contentTypeId
    ) {
        List<Place> list = findFirstRandomByCategoryAndContentType(
                category, contentTypeId, PageRequest.of(0, 1)
        );
        return list.stream().findFirst();
    }

    List<Place> findAllByContentIdIn(Collection<String> contentIds);

    List<Place> findByContentIdIn(Collection<String> contentIds);
}