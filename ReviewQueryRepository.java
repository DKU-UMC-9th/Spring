package com.koo.week6.repository; 

import com.koo.week6.entity.Review; 
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository; 

import java.util.List;

import static com.koo.week6.entity.QReview.review; 

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<Review> findReviewsByStoreAndFilter(Long storeId, List<Integer> stars, Pageable pageable) {
        
        // 1. 컨텐츠 조회 쿼리
        List<Review> content = queryFactory
                .selectFrom(review)
                .where(
                        storeIdEq(storeId),  // (필수) 가게 ID
                        starRatingIn(stars)  // (선택) 별점 필터링
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc()) 
                .fetch();

        // 2. 카운트 쿼리
        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        storeIdEq(storeId),
                        starRatingIn(stars)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    // 필수 조건: 가게 ID 조건
    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    // 선택 조건: 별점 필터링 동적 쿼리
    private BooleanExpression starRatingIn(List<Integer> stars) {
        if (stars == null || stars.isEmpty()) {
            return null;
        }
        return review.star.floor().intValue().in(stars);
    }
}