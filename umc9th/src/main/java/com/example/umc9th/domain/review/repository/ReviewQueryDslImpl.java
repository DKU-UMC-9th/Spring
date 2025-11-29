package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<Review> searchReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.member).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    // 내가 작성한 리뷰 페이지 조회
    @Override
    public Page<Review> searchReviewWithPaging(Predicate predicate, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        List<Review> reviews = queryFactory
                .selectFrom(review)
                .leftJoin(review.store).fetchJoin()
                .leftJoin(review.member).fetchJoin()
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(review.count())
                .from(review)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(reviews, pageable, total);
    }
}
