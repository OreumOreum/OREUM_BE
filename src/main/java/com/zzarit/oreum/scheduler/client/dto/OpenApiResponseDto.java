package com.zzarit.oreum.scheduler.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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
}
