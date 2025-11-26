package com.example.umc9th.domain.review.dto;

public record ReviewResponse(
    Long reviewId,
    Long userId,
    Long storeId,
    String storeName,
    String content,
    Float star
) {}