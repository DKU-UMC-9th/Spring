package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateDTO(
            Long reviewId,
            LocalDate createdAt
    ) {}

    @Builder
    public record MyReviewDTO(
            Long reviewId,
            String storeName,
            double star,
            String content,
            LocalDate createdAt
    ) {}

    @Builder
    public record MyReviewPageDTO(
            List<MyReviewDTO> contents,
            int page,
            int size,
            long totalElements,
            int totalPages
    ) {}
}
