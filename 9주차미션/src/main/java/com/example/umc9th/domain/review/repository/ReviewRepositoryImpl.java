package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.QReviewResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.umc9th.domain.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewResponse> searchReview(BooleanBuilder builder, Pageable pageable) {

        List<ReviewResponse> content = queryFactory
                .select(new QReviewResponse(
                        review.id,
                        review.content,
                        review.star,
                        review.createdAt
                ))
                .from(review)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(review.count())
                .from(review)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
