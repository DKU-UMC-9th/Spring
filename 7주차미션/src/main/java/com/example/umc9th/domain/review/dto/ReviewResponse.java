package com.example.umc9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ReviewResponse {
    private Long reviewId;
    private String content;
    private Integer star;

    @QueryProjection
    public ReviewResponse(Long reviewId, String content, Integer star) {
        this.reviewId = reviewId;
        this.content = content;
        this.star = star;
    }
}

