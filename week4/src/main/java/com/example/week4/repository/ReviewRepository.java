package com.example.week4.repository;

import com.example.week4.domain.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.week4.domain.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager entityManager;

    public List<Review> findReviewsByStoreAndFilter(Long storeId, List<Integer> stars, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        // 1. 컨텐츠 조회 (페이징 적용)
        List<Review> content = queryFactory
                .selectFrom(review)
                .where(
                        storeIdEq(storeId),
                        starRatingIn(stars)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        return content;
    }

    // 가게 ID 조건
    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    // 별점 필터링
    private BooleanExpression starRatingIn(List<Integer> stars) {
        if (stars == null || stars.isEmpty()) {
            return null;
        }
        return review.star.floor().intValue().in(stars);
    }
}