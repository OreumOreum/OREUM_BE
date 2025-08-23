package com.zzarit.oreum.member.controller;

import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.service.dto.CheckTestResponseDto;
import com.zzarit.oreum.member.service.dto.MemberResponseDto;
import com.zzarit.oreum.member.service.dto.UpdateMemberCategoryRequest;
import com.zzarit.oreum.member.service.dto.UpdateMemberProfileRequest;
import com.zzarit.oreum.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MEMBER", description = "사용자 정보 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "내 정보 조회 API", description = "로그인된 본인의 상세 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo(Member member) {
        MemberResponseDto myInfo = memberService.getMemberInfo(member);
        return ResponseEntity.ok(myInfo);
    }

    @Operation(summary = "내 유형 수정 API", description = "본인의 관심 카테고리를 변경합니다.")
    @PatchMapping("/me/category")
    public ResponseEntity<Void> updateMyCategory(
            Member member,
            @RequestBody UpdateMemberCategoryRequest request) {

        memberService.updateCategory(member.getId(), request.getCategoryType());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴 API", description = "로그인된 본인의 계정을 탈퇴 처리합니다.")
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMember(Member member) {
        memberService.deleteMember(member.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "유형검사 생략 API", description = "유형검사를 생략합니다.")
    @PostMapping("skip/test")
    public ResponseEntity<Void> skipTest(Member member) {
        memberService.skipTest(member.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "유형검사 생략 조회 API", description = "홈화면에서 유형검사를 실행여부를 조회합니다(True: 검사실행)")
    @GetMapping("test")
    public ResponseEntity<CheckTestResponseDto> getTest(Member member) {
        return ResponseEntity.ok(memberService.getTest(member));
    }


}