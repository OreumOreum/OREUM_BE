package com.zzarit.oreum.member.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.member.service.dto.MemberResponseDto;
import com.zzarit.oreum.member.service.dto.UpdateMemberProfileRequest;
import com.zzarit.oreum.member.domain.repository.CategoryRepository;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public MemberResponseDto getMemberInfo(Member member) {

        return new MemberResponseDto(member);
    }

    @Transactional
    public void updateProfile(Long memberId, UpdateMemberProfileRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. id=" + memberId));

        if (request.getName() != null) {
            member.setName(request.getName());
        }

        if (request.getEmail() != null) {
            member.setEmail(request.getEmail());
        }

        if (request.getPhoneNo() != null) {
            member.setPhoneNo(request.getPhoneNo());
        }

    }


    @Transactional
    public void updateCategory(Long memberId, String categoryType) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. id=" + memberId));

        Type typeEnum = Type.valueOf(categoryType.toUpperCase());
        Category category = categoryRepository.findById(typeEnum)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다. type=" + categoryType));

        member.setCategory(category);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. id=" + memberId);
        }
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public void skipTest(Long id){
        Member m  = memberRepository.findById(id).get();
        m.setSkip(true);
        memberRepository.save(m);
    }

    @Transactional
    public boolean getTest(Member member){
        Member m  = memberRepository.findById(member.getId()).get();
        return !member.getSkip() && m.getCategory() == null;
    }

}