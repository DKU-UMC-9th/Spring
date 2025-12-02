package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<Review> searchReviewByStore(Predicate predicate) {
        
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.user).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc()) //최근에 작성된 리뷰를 먼저 봄.
                .fetch();
    }

}