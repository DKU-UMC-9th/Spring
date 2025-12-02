package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import java.util.List;

public interface ReviewQueryService {
    // 리뷰 검색
    List<Review> searchReview(String query, String type);

    // 내가 작성한 리뷰(필터링)
    List<MyReviewResponse> searchMyReviews(Long memberId, String restaurantName, Integer star);

    // 가게의 리뷰 목록 조회
    ReviewResDTO.ReviewPreviewListDTO findReview(String restaurantName, Integer page);

    // 내가 작성한 리뷰 목록
    ReviewResDTO.ReviewPreviewListDTO findMyReview(Long memberId, Integer page);
}
