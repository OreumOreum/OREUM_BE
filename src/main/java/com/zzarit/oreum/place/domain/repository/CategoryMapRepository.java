package com.zzarit.oreum.place.domain.repository;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.SubCategory;
import com.zzarit.oreum.place.domain.CategoryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMapRepository extends JpaRepository<CategoryMap, SubCategory> {

    List<CategoryMap> findByIdCategory3(String category3);
}