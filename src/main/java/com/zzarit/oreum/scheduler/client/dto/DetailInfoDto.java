package com.zzarit.oreum.scheduler.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailInfoDto {
    @JsonProperty("subnum")
    private Integer order;

    @JsonProperty("subcontentid")
    private String contentId;

    @JsonProperty("subname")
    private String title;

    @JsonProperty("subdetailoverview")
    private String overview;

    @JsonProperty("subdetailimg")
    private String originImage;
}
