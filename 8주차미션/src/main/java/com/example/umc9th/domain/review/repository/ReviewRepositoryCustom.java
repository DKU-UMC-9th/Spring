package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<ReviewResponse> searchReview(Predicate predicate); // predicate를 사용한 동적 쿼리 메서드
}