package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import org.springframework.data.domain.Page;


public interface ReviewQueryService {

    /**
     * 사용자의 리뷰 조회 (필터링 포함) - 페이징 포함
     */
    Page<ReviewResponse> getReviewsByUserWithFilters(
            Long userId,
            Long storeId,
            Integer minStar,
            Integer maxStar,
            Integer page
    );

    ReviewResDTO.ReviewPreviewListDTO findReview(String storeName, Integer page);

}
