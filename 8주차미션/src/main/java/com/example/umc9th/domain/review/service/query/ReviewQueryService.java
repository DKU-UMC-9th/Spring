package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewQueryService {

    /**
     * 사용자의 리뷰 조회 (필터링 포함)
     */
    List<ReviewResponse> getReviewsByUserWithFilters(
            Long userId,
            Long storeId,
            Integer minStar,
            Integer maxStar
    );
}