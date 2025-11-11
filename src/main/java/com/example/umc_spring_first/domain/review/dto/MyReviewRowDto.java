package com.example.umc_spring_first.domain.review.dto;

import java.time.LocalDateTime;

public record MyReviewRowDto(
        Long reviewId,
        Long storeId,
        String storeName,
        Float rating,
        String content,
        LocalDateTime createdAt
) {}
