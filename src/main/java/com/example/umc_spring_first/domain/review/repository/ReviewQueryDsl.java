package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {

    Page<Review> searchReviews(
            Long storeId,
            Integer starBand,
            Pageable pageable
    );

    Page<Review> searchMyReviews(
            Long userId,
            Pageable pageable
    );
}

