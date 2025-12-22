package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.entity.QReview;
import com.example.umc_spring_first.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<Review> searchReviews(Long storeId, Integer starBand, Pageable pageable) {
        QReview r = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        if (storeId != null)
            builder.and(r.store.id.eq(storeId));

        if (starBand != null) {
            if (starBand == 5) {
                builder.and(r.rating.eq(5.0f));
            } else {
                builder.and(r.rating.between(starBand, starBand + 0.9999f));
            }
        }

        List<Review> content = query
                .select(r)         // Entity 반환
                .from(r)
                .where(builder)
                .orderBy(r.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(r.count())
                .from(r)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    @Override
    public Page<Review> searchMyReviews(Long userId, Pageable pageable) {
        QReview r = QReview.review;

        List<Review> content = query
                .select(r)      // Entity 반환
                .from(r)
                .where(r.user.id.eq(userId))
                .orderBy(r.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query
                .select(r.count())
                .from(r)
                .where(r.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

