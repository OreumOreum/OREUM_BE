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
        int pageNo = 0;
        int noXYCnt = 0;
        int noOverViewCnt = 0;
        while (true) {
            OpenApiResponseDto<AreaBasedDto> response = openApiClient.getAreaBasedList(pageNo++, 3000);
            if (response.getResponse().getBody().getNumOfRows() == 0) break;
            List<AreaBasedDto> tourismItemDtoList = response.getResponse().getBody().getItems().getItem();
            for(int i=0; i<tourismItemDtoList.size(); i++){
                AreaBasedDto item = tourismItemDtoList.get(i);
                String mapX = item.getMapx().replaceAll(" ","");
                String mapY = item.getMapy().replaceAll(" ","");
                if(mapX.isEmpty() || mapY.isEmpty()){
                    System.out.println("좌표 없음: " + item.getContentid());
                    noXYCnt++;
                }
            }
            for (AreaBasedDto item : tourismItemDtoList) {
                String overview =  openApiClient.getDetailCommon(item.getContentid()).getOverview();
                String formatedDto = overview.replaceAll(" ","");
                if(formatedDto.isEmpty()) {
                    System.out.println("소개글 없음: " + item.getContentid());
                    noOverViewCnt++;
                }
            }
        }

    }



}