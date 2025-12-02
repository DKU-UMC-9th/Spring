package com.example.umc_spring_first.domain.review.dto.req;

import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record CreateReviewRequest(
            Long storeId,
            Float rating,
            String content
    ) {}
}
