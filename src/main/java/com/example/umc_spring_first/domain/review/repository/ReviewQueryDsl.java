package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {

    // 전체/필터 리뷰 조회
    Page<ReviewResDTO.ReviewPreviewDTO> searchReviews(
            Long storeId,
            Integer starBand,
            Pageable pageable
    );

    // 내가 작성한 리뷰 조회
    Page<ReviewResDTO.ReviewPreviewDTO> searchMyReviews(
            Long userId,
            Pageable pageable
    );
}
