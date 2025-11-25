package com.example.umc9th.domain.review.dto.res;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MyReviewResponse{
        private Long id;
        private String content;
        private float star;
        private String reply;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CreateReviewResponse{
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    public record ReviewPreviewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreviewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}
