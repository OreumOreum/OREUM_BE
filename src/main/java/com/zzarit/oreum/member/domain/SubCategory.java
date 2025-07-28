package com.zzarit.oreum.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory implements Serializable {
    @Column(name = "category3", length = 20)
    private String category3;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 30)
    private Type type;
}
