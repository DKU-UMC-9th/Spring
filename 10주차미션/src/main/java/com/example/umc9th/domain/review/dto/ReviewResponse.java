package com.example.umc9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private Long reviewId;
    private String content;
    private Integer star;
    private LocalDateTime createdAt;   // ← 추가

    @QueryProjection
    public ReviewResponse(Long reviewId, String content, Integer star, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.content = content;
        this.star = star;
        this.createdAt = createdAt;   // ← 추가
    }
}


