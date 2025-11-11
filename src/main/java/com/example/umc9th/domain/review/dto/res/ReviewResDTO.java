package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MyReviewResponse{
        private Long id;
        private String content;
        private float star;
        private String reply;

    }
}
