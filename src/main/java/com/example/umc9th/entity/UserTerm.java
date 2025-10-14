package com.example.umc9th.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_term")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserTerm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;
}
