package com.example.umc_spring_first.domain.review.dto.res;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewPreviewDTO(
            Long reviewId,
            String storeName,
            Float rating,
            String content,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewPreviewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MyReviewResponse(
            Long reviewId,
            String storeName,
            Float rating,
            String content,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
