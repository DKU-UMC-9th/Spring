package com.example.week4.service;

import com.example.week4.domain.Review;
import com.example.week4.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review createReview(Long storeId, ReviewRequestDTO.CreateReviewDto request);
}
