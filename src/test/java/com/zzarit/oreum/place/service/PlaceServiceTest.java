package com.zzarit.oreum.place.service;

import com.zzarit.oreum.global.exception.NotFoundException;
import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Member;
import com.zzarit.oreum.member.domain.Type;
import com.zzarit.oreum.member.domain.repository.CategoryRepository;
import com.zzarit.oreum.member.domain.repository.MemberRepository;
import com.zzarit.oreum.place.domain.*;
import com.zzarit.oreum.place.domain.repository.*;
import com.zzarit.oreum.place.service.dto.CourseResponseDto;
import com.zzarit.oreum.place.service.dto.ReviewCreateRequestDto;
import com.zzarit.oreum.util.CategoryFixture;
import com.zzarit.oreum.util.CourseCategoryFixture;
import com.zzarit.oreum.util.CourseFixture;
import com.zzarit.oreum.util.MemberFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;
    @Autowired
    private CourseRepository courseRepository;



    @Test
    @Transactional
    public void 유형O코스조회(){
        Category category = categoryRepository.save(CategoryFixture.category(Type.ACTIVITY));
        Member member = memberRepository.save(MemberFixture.member(category));
        List<Course> courses = CourseFixture.courses(3);
        List<CourseCategory> cc = CourseCategoryFixture.linkCoursesToCategory(courses,category);
        courseRepository.saveAll(courses);
        courseCategoryRepository.saveAll(cc);


        List<CourseResponseDto> list = courseService.getCourseList(member);

        Assertions.assertThat(list.size()).isEqualTo(3);
    }


    @Test
    @Transactional
    public void 유형X코스조회(){
        Category category1 = categoryRepository.save(CategoryFixture.category(Type.ACTIVITY));
        Category category2 = categoryRepository.save(CategoryFixture.category(Type.FOOD));
        Member member = memberRepository.save(MemberFixture.member());
        List<Course> courses1 = CourseFixture.courses(3);
        List<Course> courses2 = CourseFixture.courses(3);
        List<CourseCategory> cc1 = CourseCategoryFixture.linkCoursesToCategory(courses1,category1);
        List<CourseCategory> cc2 = CourseCategoryFixture.linkCoursesToCategory(courses2,category2);
        courseRepository.saveAll(courses1);
        courseRepository.saveAll(courses2);
        courseCategoryRepository.saveAll(cc1);
        courseCategoryRepository.saveAll(cc2);


        List<CourseResponseDto> list = courseService.getCourseList(member);

        Assertions.assertThat(list.size()).isEqualTo(6);
    }

}