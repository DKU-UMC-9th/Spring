package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    /**
     * QueryDSL 조회 결과 → 응답 DTO 변환
     */
    public static ReviewResDTO.ReviewListDTO toReviewListDTO(List<ReviewResponse> reviews) {
        List<ReviewResDTO.ReviewDTO> reviewDTOs = reviews.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .totalCount(reviewDTOs.size())
                .build();
    }

    /**
     * 개별 리뷰 변환
     */
    private static ReviewResDTO.ReviewDTO toReviewDTO(ReviewResponse review) {
        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }
}
