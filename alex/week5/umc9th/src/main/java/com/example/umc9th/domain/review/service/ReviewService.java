package com.example.umc9th.domain.review.service;

import java.util.List;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestCreate;

public interface ReviewService {
    
    ReviewResponse createReview(ReviewRequestCreate reqeust);
    List<ReviewResponse> search(Long storeId);

}