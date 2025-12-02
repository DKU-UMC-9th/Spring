package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id") // DB 컬럼명 명시
    private Long id;

    @Column(name = "term_name", nullable = false) // ENUM 컬럼만 남김
    @Enumerated(EnumType.STRING)
    private TermName name;
}
