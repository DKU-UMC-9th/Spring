// src/main/java/com/example/umc_spring_first/domain/prefer/entity/Prefer.java
package com.example.umc_spring_first.domain.prefer.entity;

import com.example.umc_spring_first.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prefer")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Prefer extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu", nullable = false, length = 100)
    private String menu;
}
