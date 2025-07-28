package com.zzarit.oreum.member.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_map")
@NoArgsConstructor
public class CategoryMap {

    @EmbeddedId
    private SubCategory id;

    @MapsId("type")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_type", nullable = false)
    private Category categoryType;

    // 편의 생성자
    public CategoryMap(String category3, Category category) {
        this.id = new SubCategory(category3, category.getType());
        this.categoryType = category;
    }


}
