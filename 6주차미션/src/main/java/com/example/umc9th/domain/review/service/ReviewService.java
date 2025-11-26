package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse> getReviewsByUserWithFilters(Long userId, Long storeId, Integer minStar, Integer maxStar) {
        // Repository에서 바로 DTO Projection
        return reviewRepository.findReviewsByUserWithFilters(userId, storeId, minStar, maxStar);
    }
}
