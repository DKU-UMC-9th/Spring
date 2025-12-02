package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO.CreateReviewRequest;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.CreateReviewResponse;

public interface ReviewCommandService {
    // 리뷰 작성하기
    CreateReviewResponse createReview(Long restaurantId, CreateReviewRequest request);
}
