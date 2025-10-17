package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "review_image")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="review_id", nullable=false)
    private Review review;

    @Column(nullable=false, length=200)
    private String url;
}