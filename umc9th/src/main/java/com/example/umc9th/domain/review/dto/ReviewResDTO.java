package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;

public class ReviewResDTO {

    @Builder
    public record CreateDTO(
            Long reviewId,
            LocalDate createdAt
    ) {}
}
