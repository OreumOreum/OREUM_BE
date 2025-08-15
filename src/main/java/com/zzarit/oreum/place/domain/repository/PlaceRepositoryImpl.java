package com.zzarit.oreum.place.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zzarit.oreum.member.domain.QCategory;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.place.domain.Place;
import com.zzarit.oreum.place.domain.QPlace;
import com.zzarit.oreum.place.domain.QPlaceCategory;
import com.zzarit.oreum.place.domain.QReview;
import com.zzarit.oreum.place.service.dto.PlaceSearchConditionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Place> searchPlaces(PlaceSearchConditionDto condition, Pageable pageable, Integer sigunguCode) {
        QPlace place = QPlace.place;

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.keyword())) {
            builder.or(place.title.containsIgnoreCase(condition.keyword()));
            builder.or(place.address.containsIgnoreCase(condition.keyword()));
            builder.or(place.tel.containsIgnoreCase(condition.keyword()));
        }

        if (sigunguCode != null) {
            builder.and(place.sigunguCode.eq(sigunguCode));
        }

        List<Place> content = queryFactory
                .selectFrom(place)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(
                queryFactory
                        .select(place.count())
                        .from(place)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<Place> findPlaceList(String contentTypeId,
                                     Integer sigunguCode,
                                     Type type,
                                     Pageable pageable) {
        QPlace place = QPlace.place;
        QPlaceCategory placeCategory = QPlaceCategory.placeCategory;
        QCategory category = QCategory.category;
        QReview review = QReview.review;

        // 1) wehere절 동적 쿼리 (관광지타입, 제주/서귀포, 검사유형)
        BooleanBuilder builder = new BooleanBuilder();
        if (contentTypeId != null) {
            builder.and(place.contentTypeId.eq(contentTypeId));
        }
        if (sigunguCode != null) {
            builder.and(place.sigunguCode.eq(sigunguCode));
        }
        if (type != null) {
            builder.and(category.type.eq(type));
        }

        JPAQuery<Place> query = queryFactory
                .selectDistinct(place)
                .from(place)
                .leftJoin(place.placeCategories, placeCategory)
                .leftJoin(placeCategory.category, category)
                .where(builder);


        // 2) OrderBy 동적쿼리 (리뷰좋은순, 랜덤)
        Sort.Order reviewOrder = pageable.getSort().getOrderFor("review");
        Sort.Order randomOrder = pageable.getSort().getOrderFor("random");


        if (reviewOrder != null) {
            SubQueryExpression<Double> avgRatingSubquery = JPAExpressions
                    .select(review.rate.avg())
                    .from(review)
                    .where(review.place.eq(place));

            OrderSpecifier<Double> orderSpec = new OrderSpecifier<>(
                    reviewOrder.isAscending() ? Order.ASC : Order.DESC,
                    avgRatingSubquery  // SubQueryExpression<Double> 타입
            ).nullsLast();

            query.orderBy(orderSpec);
        }else if (randomOrder != null) {
            // DB 타입에 따라 RAND() 또는 RANDOM() 사용
            query.orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc());
        }

        // 3) 페이징 및 결과 조회
        List<Place> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 4) 전체 카운트 (정렬 무시)
        long count = queryFactory
                .select(place.countDistinct())
                .from(place)
                .leftJoin(place.placeCategories, placeCategory)
                .leftJoin(placeCategory.category, category)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }
}