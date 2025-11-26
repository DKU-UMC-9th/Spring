package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.QReviewResponse;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewResponse> findReviewsByUserWithFilters(Long userId, Long storeId, Integer minStar, Integer maxStar) {
        QReview review = QReview.review;

        var query = queryFactory
                .select(new QReviewResponse(
                        review.id,
                        review.content,
                        review.star
                ))
                .from(review)
                .where(review.user.id.eq(userId));

        if (storeId != null) query.where(review.store.id.eq(storeId));
        if (minStar != null && maxStar != null) query.where(review.star.between(minStar, maxStar));
        else if (minStar != null) query.where(review.star.goe(minStar));
        else if (maxStar != null) query.where(review.star.loe(maxStar));

        return query.orderBy(review.id.desc()).fetch();
    }
}
