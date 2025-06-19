package com.oreum.zzarit.auth.domain;

import com.oreum.zzarit.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "auth")
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    private String sessionId;

    @NotNull
    private String refreshToken;

    public RefreshToken(Member member, String sessionId, String refreshToken) {
        this(null, member, sessionId, refreshToken);
    }

    public boolean isValid(String refreshToken) {
        return this.refreshToken.equals(refreshToken);
    }

    public void refresh(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}