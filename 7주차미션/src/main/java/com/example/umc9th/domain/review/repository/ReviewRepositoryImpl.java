package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.QReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)  // 조회 작업이므로 readOnly
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResponse> searchReview(Predicate predicate) { // predicate를 사용한 동적 쿼리 메서드
        // Q 클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .select(new QReviewResponse(
                        review.id,
                        review.content,
                        review.star
                ))
                .from(review)
                .leftJoin(review.store, store)  // Store 정보가 필요하면 join
                .where(predicate)  // 동적으로 전달받은 조건 적용
                .orderBy(review.id.desc()) // 최신 리뷰가 먼저 오도록 정렬
                .fetch();
    }

}
