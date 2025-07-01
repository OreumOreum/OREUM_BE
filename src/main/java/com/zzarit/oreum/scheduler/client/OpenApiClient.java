package com.zzarit.oreum.scheduler.client;

import com.zzarit.oreum.scheduler.client.dto.AreaBasedDto;
import com.zzarit.oreum.scheduler.client.dto.DetailCommonDto;
import com.zzarit.oreum.scheduler.client.dto.OpenApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenApiClient {
    private static final String GET_AREA_BASED_URI = "https://apis.data.go.kr/B551011/KorService2/areaBasedList2";
    private static final String GET_DETAIL_COMMON_URI = "https://apis.data.go.kr/B551011/KorService2/detailCommon2";

    @Value("${OPEN_API_SECRET_KEY}")
    private String OPEN_API_SECRET_KEY;

    private final RestClient restClient;

    public OpenApiResponseDto<AreaBasedDto> getAreaBasedList(int pageNo, int numOfRows) {
        URI uri = baseOpenApiBuilder(GET_AREA_BASED_URI)
                .queryParam("pageNo",pageNo)
                .queryParam("numOfRows",numOfRows).queryParam("areaCode", "39")
                .build(true)
                .toUri();

        OpenApiResponseDto<AreaBasedDto> response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<OpenApiResponseDto<AreaBasedDto>>() {});

        return response;
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

        return responseDto.getResponse().getBody().getItems().getItem().get(0);
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
