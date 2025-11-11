package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchReview(Predicate predicate); //동적 쿼리를 위해 Predicate 객체 사용
}