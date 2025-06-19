package com.zzarit.oreum.auth.controller;

import com.zzarit.oreum.auth.config.AuthTestConfig;
import com.zzarit.oreum.auth.service.client.KakaoAuthClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@Import(AuthTestConfig.class)
class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KakaoAuthClient kakaoAuthClient;


    @Test
    void kakaoLogin_통합테스트() throws Exception {
        // given
        String fakeLoginId = "KAKAO1";
        when(kakaoAuthClient.getUserInfo("fake-token")).thenReturn(fakeLoginId);

        String requestJson = """
            {
                "accessToken": "fake-token"
            }
            """;

        // when & then
        mockMvc.perform(post("/v1/auth/login/kakao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

}