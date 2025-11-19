package com.example.demo.domain.review.entity;

import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "review_image")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewImage extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="review_id", nullable=false)
    private Review review;

    @Column(nullable=false, length=200)
    private String url;
}