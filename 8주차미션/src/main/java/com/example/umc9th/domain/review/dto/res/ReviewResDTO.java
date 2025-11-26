package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        private List<ReviewDTO> reviews;
        private Integer totalCount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDTO {
        private Long reviewId;
        private String content;
        private Integer star;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }
}