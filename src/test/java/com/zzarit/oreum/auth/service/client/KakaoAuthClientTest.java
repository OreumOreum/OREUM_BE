package com.zzarit.oreum.auth.service.client;

import com.zzarit.oreum.auth.exception.KaKaoLoginFailException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoAuthClientTest {

    @Autowired
    private KakaoAuthClient kakaoAuthClient;

    @DisplayName("액세스 토큰 에러")
    @Test
    void errorMessageTest(){

        Assertions.assertThatThrownBy(()->
            kakaoAuthClient.getUserInfo("error")
        ).isInstanceOf(KaKaoLoginFailException.class);

    }

}