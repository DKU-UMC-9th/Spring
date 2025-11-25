package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface ReviewQueryDsl {

    //검색 API
    List<Review> searchReview(Predicate predicate);

    // 내 리뷰 보기 API
    List<MyReviewResponse> searchMyReviews(Predicate predicate);
}
