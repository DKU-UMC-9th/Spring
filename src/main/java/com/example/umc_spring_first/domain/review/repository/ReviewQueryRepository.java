package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    /**
     * 내가 작성한 리뷰 조회 (단일 API)
     * @param userId    (필수) 작성자
     * @param storeId   (선택) 특정 가게만
     * @param starBand  (선택) 별점대: 5,4,3,2,1 (null 이면 전체)
     */
    Page<MyReviewRowDto> findMyReviews(Long userId, Long storeId, Integer starBand, Pageable pageable);
}
