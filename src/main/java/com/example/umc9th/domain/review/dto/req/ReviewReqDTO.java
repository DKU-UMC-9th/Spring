package com.example.umc9th.domain.review.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CreateReviewRequest{
        private float star;
        private String content;
    }

}
