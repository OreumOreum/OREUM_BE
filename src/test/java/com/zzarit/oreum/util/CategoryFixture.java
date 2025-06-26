package com.zzarit.oreum.util;

import com.zzarit.oreum.member.domain.Category;
import com.zzarit.oreum.member.domain.Type;

public class CategoryFixture {

    public static Category category(Type type) {
        Category category = new Category();
        category.setType(type);
        return category;
    }
}
