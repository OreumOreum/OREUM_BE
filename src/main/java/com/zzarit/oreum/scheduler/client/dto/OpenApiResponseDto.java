package com.zzarit.oreum.scheduler.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiResponseDto<T> {
    private Response<T> response;

    @Data
    @NoArgsConstructor
    public static class Response<T> {
        private Header header;
        private Body<T> body;
    }

    @Data
    @NoArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    @NoArgsConstructor
    public static class Body<T> {
        private Items<T> items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Data
    @NoArgsConstructor
    public static class Items<T> {
        private List<T> item;
    }

    public Optional<T> firstItem() {
        return Optional.ofNullable(response)
                .map(Response::getBody)
                .map(Body::getItems)
                .map(Items::getItem)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0));
    }
}
