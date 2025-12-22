package com.example.week4.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDto {
        private Long reviewId;
        private LocalDateTime createdAt;

        public static CreateReviewResultDto from(com.example.week4.domain.Review review) {
            return CreateReviewResultDto.builder()
                    .reviewId(review.getId())
                    .createdAt(java.time.LocalDateTime.now())
                    .build();
        }
    }
}
