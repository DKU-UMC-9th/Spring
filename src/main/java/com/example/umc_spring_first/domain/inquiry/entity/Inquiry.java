package com.example.umc_spring_first.domain.inquiry.entity;

import com.example.umc_spring_first.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Inquiry")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Inquiry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
