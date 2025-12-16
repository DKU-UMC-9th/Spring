package com.example.umc_spring_first.domain.review.dto;

import java.time.LocalDateTime;

public record MyReviewRowDto( //조회용 DTO -> 조회 결과를 프론트에 내려줄 때 사용
        Long reviewId,
        Long storeId,
        String storeName,
        Float rating,
        String content,
        LocalDateTime createdAt
) {}
