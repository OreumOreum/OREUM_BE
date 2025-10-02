package com.zzarit.oreum.member.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.member.service.dto.CheckTestResponseDto;
import com.zzarit.oreum.member.service.dto.MemberResponseDto;
import com.zzarit.oreum.member.service.dto.UpdateMemberProfileRequest;
import com.zzarit.oreum.member.domain.repository.CategoryRepository;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 관리 서비스 클래스
 * 
 * 회원 정보 조회, 카테고리 업데이트, 회원 탈퇴, 유형 검사 관련 기능을 제공합니다.
 * 대부분의 조회 작업은 읽기 전용 트랜잭션으로 처리되며, 
 * 데이터 변경이 필요한 작업만 별도로 쓰기 트랜잭션을 사용합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 회원 정보를 조회합니다.
     * 
     * @param member 정보를 조회할 회원 엔티티
     * @return MemberResponseDto 회원 정보 응답 DTO
     */
    public MemberResponseDto getMemberInfo(Member member) {

        return new MemberResponseDto(member);
    }


    /**
     * 회원의 관심 카테고리를 업데이트합니다.
     * 
     * 유형 검사 결과에 따라 회원의 관심 카테고리를 설정합니다.
     * 이 정보는 맞춤형 컨텐츠 추천에 활용됩니다.
     * 
     * @param memberId 카테고리를 변경할 회원 ID
     * @param typeEnum 새로운 카테고리 타입
     * @throws IllegalArgumentException 회원 또는 카테고리를 찾을 수 없는 경우
     */
    @Transactional
    public void updateCategory(Long memberId, Type typeEnum) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. id=" + memberId));

        Category category = categoryRepository.findById(typeEnum)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다. type=" + typeEnum));

        member.setCategory(category);
    }

    /**
     * 회원 탈퇴를 처리합니다.
     * 
     * 회원과 관련된 모든 데이터(폴더, 플래너, 리뷰 등)가 함께 삭제됩니다.
     * 
     * @param memberId 탈퇴할 회원 ID
     * @throws IllegalArgumentException 회원을 찾을 수 없는 경우
     */
    @Transactional
    public void deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. id=" + memberId);
        }
        memberRepository.deleteById(memberId);
    }

    /**
     * 유형 검사를 생략하도록 설정합니다.
     * 
     * 회원이 유형 검사를 생략하기를 원하는 경우 해당 플래그를 설정합니다.
     * 
     * @param id 유형 검사를 생략할 회원 ID
     */
    @Transactional
    public void skipTest(Long id){
        Member m  = memberRepository.findById(id).get();
        m.setSkip(true);
        memberRepository.save(m);
    }

    /**
     * 유형 검사 실행 여부를 확인합니다.
     * 
     * 회원이 유형 검사를 생략하지 않았고, 아직 카테고리가 설정되지 않은 경우
     * true를 반환하여 유형 검사를 진행하도록 안내합니다.
     * 
     * @param member 확인할 회원 엔티티
     * @return CheckTestResponseDto 유형 검사 실행 여부 (true: 검사 실행 필요, false: 검사 불필요)
     */
    @Transactional
    public CheckTestResponseDto getTest(Member member){
        Member m  = memberRepository.findById(member.getId()).get();
        return new CheckTestResponseDto(!member.getSkip() && m.getCategory() == null);
    }

}