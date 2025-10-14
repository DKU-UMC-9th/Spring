package com.example.umc9th.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interest")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Interest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
}
