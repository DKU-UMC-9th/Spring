package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<MyReviewRowDto> findMyReviews(Long userId, Long storeId, Integer starBand, Pageable pageable);
    Page<MyReviewRowDto> findStoreReviews(Long storeId, Integer starBand, Pageable pageable);
    Page<MyReviewRowDto> findReviewsByStar(Integer starBand, Pageable pageable);
}
