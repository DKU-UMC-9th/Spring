package com.example.umc_spring_first.domain.review.service;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import com.example.umc_spring_first.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<MyReviewRowDto> getReviews(Long storeId, Integer starBand, Pageable pageable) {
        return reviewRepository.findReviews(storeId, starBand, pageable);
    }
}