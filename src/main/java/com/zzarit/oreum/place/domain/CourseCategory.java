package com.zzarit.oreum.place.domain;


import com.zzarit.oreum.member.domain.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "course_category")
@NoArgsConstructor
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_type")
    private Category category;

    public CourseCategory(Course course, Category category) {
        this.course = course;
        this.category = category;
    }
}
