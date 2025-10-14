package com.example.umc9th.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_interest")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserInterest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id", nullable = false)
    private Interest interest;
}
