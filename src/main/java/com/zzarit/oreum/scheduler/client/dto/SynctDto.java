package com.zzarit.oreum.scheduler.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SynctDto {
    private String addr1;            // 주소
    private String addr2;            // 상세주소
    private String cat1;             // 대분류코드
    private String cat2;             // 중분류코드
    private String cat3;             // 소분류코드
    private String contentid;        // 콘텐츠 ID
    private String contenttypeid;    // 콘텐츠 타입 ID
    private String createdtime;      // 등록일
    private String firstimage;       // 대표이미지 (원본)
    private String firstimage2;      // 대표이미지 (썸네일)
    private String mapx;             // GPS X좌표
    private String mapy;             // GPS Y좌표
    private String modifiedtime;     // 수정일
    private String sigungucode;      // 시군구 코드
    private String tel;              // 전화번호
    private String title;            // 콘텐츠 제목
    private String showflag;            // 표출, 비표출 여부

    @JsonSetter("firstimage")
    public void setFirstimage(String image) {
        // 스프링 유틸을 쓰면 편리합니다.
        if (image == null || !StringUtils.hasText(image.trim())) {
            this.firstimage = null;
        } else {
            this.firstimage = image;
        }
    }

    @JsonSetter("firstimage2")
    public void setFirstimage2(String image) {
        // 스프링 유틸을 쓰면 편리합니다.
        if (image == null || !StringUtils.hasText(image.trim())) {
            this.firstimage2 = null;
        } else {
            this.firstimage2 = image;
        }
    }
}
