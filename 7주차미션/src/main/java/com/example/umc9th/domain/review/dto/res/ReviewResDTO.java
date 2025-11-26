package com.example.umc9th.domain.review.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResDTO {

    /**
     * 리뷰 목록 응답 DTO
     * result 안에 들어갈 데이터
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        private List<ReviewDTO> reviews;
        private Integer totalCount;
    }

    /**
     * 개별 리뷰 DTO
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDTO {
        private Long reviewId;
        private String content;
        private Integer star;
    }
}