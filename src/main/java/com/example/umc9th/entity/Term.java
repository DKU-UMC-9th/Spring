package com.example.umc9th.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "term")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Term {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermType name;

    public enum TermType {
        SERVICE, PRIVACY, MARKETING
    }
}
