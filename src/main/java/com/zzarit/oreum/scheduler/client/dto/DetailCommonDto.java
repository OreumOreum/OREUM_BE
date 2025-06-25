package com.zzarit.oreum.scheduler.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DetailCommonDto {
    private String contentid;         // 콘텐츠 ID
    private String contenttypeid;     // 콘텐츠 타입 ID
    private String createdtime;       // 등록일 (콘텐츠 최초 등록일)
    private String homepage;          // 홈페이지 주소
    private String modifiedtime;      // 수정일 (콘텐츠 수정일)
    private String tel;               // 전화번호
    private String telname;           // 전화번호명
    private String title;             // 콘텐츠명(제목)
    private String firstimage;        // 대표 이미지 (원본)
    private String firstimage2;       // 대표 이미지 (썸네일)
    private String cpyrhtDivCd;       // 저작권 유형
    private String areacode;          // 지역 코드
    private String sigungucode;       // 시군구 코드
    private String cat1;              // 대분류 코드
    private String cat2;              // 중분류 코드
    private String cat3;              // 소분류 코드
    private String addr1;             // 주소
    private String addr2;             // 상세 주소
    private String zipcode;           // 우편번호
    private String mapx;              // GPS X좌표 (경도)
    private String mapy;              // GPS Y좌표 (위도)
    private String mlevel;            // Map Level
    private String overview;          // 콘텐츠 개요
    @JsonProperty("lDongRegnCd")
    private String lDongRegnCd;      // 법정동 시도 코드
    @JsonProperty("lDongSignguCd")   // 법정동 시군구 코드
    private String lDongSignguCd;
    private String lclsSystm1;        // 분류체계 대분류
    private String lclsSystm2;        // 분류체계 중분류
    private String lclsSystm3;        // 분류체계 소분류
}
