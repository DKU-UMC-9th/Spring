package com.example.umc9th.domain.review.service;

import java.util.List;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestCreate;
import com.example.umc9th.global.common.PageResponse;

public interface ReviewService {

    ReviewResponse createReview(ReviewRequestCreate reqeust);
    List<ReviewResponse> search(Long storeId);
    PageResponse<ReviewResponse> getMyReviews(Long userId, int page);

}