package com.example.umc9th.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ReviewResponse {
    private Long reviewId;
    private String content;
    private Float star;

    @QueryProjection
    public ReviewResponse(Long reviewId, String content, Float star) {
        this.reviewId = reviewId;
        this.content = content;
        this.star = star;
    }
}
