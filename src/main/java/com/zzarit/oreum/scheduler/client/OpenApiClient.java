package com.zzarit.oreum.scheduler.client;

import com.zzarit.oreum.scheduler.client.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenApiClient {
    private static final String GET_AREA_BASED_URI = "https://apis.data.go.kr/B551011/KorService2/areaBasedList2";
    private static final String GET_DETAIL_COMMON_URI = "https://apis.data.go.kr/B551011/KorService2/detailCommon2";
    private static final String GET_DETAIL_INFO_URL = "https://apis.data.go.kr/B551011/KorService2/detailInfo2";
    private static final String GET_AREA_BASED_SYNC_URL = "https://apis.data.go.kr/B551011/KorService2/areaBasedSyncList2";

    @Value("${OPEN_API_SECRET_KEY}")
    private String OPEN_API_SECRET_KEY;

    private final RestClient restClient;

    public List<AreaBasedDto> getAreaBasedList(int pageNo, int numOfRows) {
        URI uri = baseOpenApiBuilder(GET_AREA_BASED_URI)
                .queryParam("pageNo",pageNo)
                .queryParam("numOfRows",numOfRows)
                .queryParam("areaCode", "39")
                .build(true)
                .toUri();

        OpenApiResponseDto<AreaBasedDto> response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<OpenApiResponseDto<AreaBasedDto>>() {});


        return response.allItems().orElse(null);
    }

    public DetailCommonDto getDetailCommon(String contentId) {
        URI uri = baseOpenApiBuilder(GET_DETAIL_COMMON_URI)
                .queryParam("contentId", contentId)
                .build(true)
                .toUri();

        OpenApiResponseDto<DetailCommonDto> responseDto=  restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<OpenApiResponseDto<DetailCommonDto>>() {});

        return responseDto.firstItem().orElse(null);
    }

    public List<DetailInfoDto> getDetailInfo(String contentId) {
        URI uri = baseOpenApiBuilder(GET_DETAIL_INFO_URL)
                .queryParam("contentId", contentId)
                .queryParam("contentTypeId", 25)
                .build(true)
                .toUri();

        OpenApiResponseDto<DetailInfoDto> responseDto=  restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<OpenApiResponseDto<DetailInfoDto>>() {});

        return responseDto.allItems().orElse(null);
    }

    public List<SynctDto> getAreaBasedSyncList2() {
        URI uri = baseOpenApiBuilder(GET_AREA_BASED_SYNC_URL)
                .queryParam("arrange", "C")
                .build(true)
                .toUri();

        OpenApiResponseDto<SynctDto> responseDto=  restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<OpenApiResponseDto<SynctDto>>() {});

        return responseDto.allItems().orElse(null);
    }

    private UriComponentsBuilder baseOpenApiBuilder(String uri) {
        return UriComponentsBuilder
                .fromHttpUrl(uri)
                .queryParam("MobileOS", "WEB")
                .queryParam("MobileApp", "zzarit")
                .queryParam("_type", "json")
                .queryParam("serviceKey", OPEN_API_SECRET_KEY)
                ;
    }
}
