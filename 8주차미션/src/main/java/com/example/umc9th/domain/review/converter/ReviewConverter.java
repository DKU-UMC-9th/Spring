package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewListDTO toReviewListDTO(List<ReviewResponse> reviews) {
        List<ReviewResDTO.ReviewDTO> reviewDTOs = reviews.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .totalCount(reviewDTOs.size())
                .build();
    }

    private static ReviewResDTO.ReviewDTO toReviewDTO(ReviewResponse review) {
        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }

    // Review 엔티티 생성
    public static Review toReview(ReviewReqDTO.CreateReviewDTO dto, User user, Store store) {
        return Review.builder()
                .star(dto.getStar())
                .content(dto.getContent())
                .user(user)
                .store(store)
                .build();
    }

    // 생성 결과 DTO
    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}