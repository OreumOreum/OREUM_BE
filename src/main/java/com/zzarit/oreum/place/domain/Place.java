package com.zzarit.oreum.place.domain;

import com.zzarit.oreum.folder.domain.FolderPlace;
import com.zzarit.oreum.global.domain.BaseTimeEntity;
import com.zzarit.oreum.place.domain.detail.*;
import com.zzarit.oreum.place.service.dto.*;
import com.zzarit.oreum.planner.domain.PlannerPlace;
import com.zzarit.oreum.spot.domain.Spot;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "place")
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "sigungucode")
    private Integer sigunguCode;

    @Comment("대분류")
    @Column(name = "category_1")
    private String category1;

    @Comment("중분류")
    @Column(name = "category_2")
    private String category2;

    @Comment("소분류")
    @Column(name = "category_3")
    private String category3;

    @Comment("콘텐츠 ID")
    @Column(name = "content_id")
    private String contentId;

    @Comment("관광타입 분류")
    @Column(name = "content_type_id")
    private String contentTypeId;

    @Comment("대표이미지(원본)")
    @Column(name = "origin_image")
    private String originImage;

    @Comment("대표이미지(썸네일)")
    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Comment("저작권 유형 - TYPE1/TYPE3")
    @Column(name = "copyright_type")
    private String copyrightType;

    @Comment("x좌표")
    @Column(name = "mapx")
    private Double mapx;

    @Comment("y좌표")
    @Column(name = "mapy")
    private Double mapy;

    @Comment("전화번호")
    @Column(name = "tel")
    private String tel;

    @Comment("관광지명")
    @Column(name = "title")
    private String title;

    @Comment("상세설명")
    @Column(name = "overview")
    private String overview ;

    @Comment("법정동 시군구코드 - 제주도시(110)/서귀포시(130)")
    @Column(name = "city_code")
    private String cityCode;

    @Builder.Default
    @OneToMany(mappedBy = "place")
    private List<PlaceCategory> placeCategories = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place")
    private List<Spot> spots = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<FolderPlace> folderPlaces = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<PlannerPlace> plannerPlaces = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "culture_detail_id")
    private CultureDetail cultureDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_detail_id")
    private FestivalDetail festivalDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_detail_id")
    private FoodDetail foodDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leports_detail_id")
    private LeportsDetail leportsDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lodge_detail_id")
    private LodgeDetail lodgeDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_detail_id")
    private ShoppingDetail shoppingDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_detail_id")
    private TourDetail tourDetail;



    public Object getDetailInfo() {
        return switch (this.getContentTypeId()) {
            case "12" -> TourDetailDto.from(this.tourDetail);
            case "14" -> CultureDetailDto.from(this.cultureDetail);
            case "15" -> FestivalDetailDto.from(this.festivalDetail);
            case "28" -> LeportsDetailDto.from(this.leportsDetail);
            case "32" -> LodgeDetailDto.from(this.lodgeDetail);
            case "38" -> ShoppingDetailDto.from(this.shoppingDetail);
            case "39" -> FoodDetailDto.from(this.foodDetail);
            default -> null;
        };
    }

}