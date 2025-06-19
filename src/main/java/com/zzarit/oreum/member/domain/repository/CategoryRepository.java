package com.zzarit.oreum.member.domain.repository;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Type> {
}