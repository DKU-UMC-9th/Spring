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

    private final EntityManager em; //데이터베이스와 통신할 때 사용하는 핵심 인터페이스

    @Override
    public List<Review> searchReview(Predicate predicate) {
        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store).fetchJoin()   // 리뷰와 가게를 한 번에 로드
                .leftJoin(review.member).fetchJoin()  // 리뷰와 회원을 한 번에 로드
                .where(predicate)
                .orderBy(review.createdAt.desc())     // 최신순 정렬
                .fetch();
    }
}
