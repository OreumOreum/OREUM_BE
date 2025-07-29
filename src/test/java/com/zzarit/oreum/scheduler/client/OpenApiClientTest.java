package com.zzarit.oreum.scheduler.client;

import com.zzarit.oreum.scheduler.client.dto.AreaBasedDto;
import com.zzarit.oreum.scheduler.client.dto.DetailCommonDto;
import com.zzarit.oreum.scheduler.client.dto.OpenApiResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OpenApiClientTest {

    @Autowired
    private OpenApiClient openApiClient;

    @Test
    public void xyExistTest() {
        DetailCommonDto detailCommon = openApiClient.getDetailCommon("2755053");
        System.out.println(detailCommon.getOverview());
    }



}