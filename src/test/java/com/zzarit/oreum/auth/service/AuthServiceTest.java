package com.zzarit.oreum.auth.service;

import com.zzarit.oreum.auth.service.dto.KakaoLoginRequestDto;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void 기본폴더자동생성(){
        String loginId = "test123";
        KakaoLoginRequestDto requestDto = new KakaoLoginRequestDto(loginId);
        authService.kakaoLogin(requestDto);
        Member member = memberRepository.findByLoginId(loginId).get();

        Assertions.assertThat(member.getFolders().size()).isEqualTo(1);
    }
}