package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "foodmarket")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FoodMarket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=40)
    private String name;

    @Column(nullable=false, length=50)
    private String address;

    @Column(nullable=false)
    private Boolean open;

    @Column(nullable=false, length=200)
    private String content;
}
