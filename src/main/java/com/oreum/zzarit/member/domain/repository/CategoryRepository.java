package com.oreum.zzarit.member.domain.repository;

import com.oreum.zzarit.member.domain.Category;
import com.oreum.zzarit.member.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Type> {
}