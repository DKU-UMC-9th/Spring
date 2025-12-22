package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<MyReviewRowDto> findReviews(Long storeId, Integer starBand, Pageable pageable);
}
