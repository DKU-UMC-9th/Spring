package com.example.umc_spring_first.domain.user.entity;

import com.example.umc_spring_first.domain.user.enums.TermName;
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
    @Column(name = "term_id")
    private Long id;

    @Column(name = "term_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName name;
}