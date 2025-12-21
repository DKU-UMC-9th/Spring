package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<ReviewResponse> findReviewsByUserWithFilters(Long userId, Long storeId, Integer minStar, Integer maxStar);
}
