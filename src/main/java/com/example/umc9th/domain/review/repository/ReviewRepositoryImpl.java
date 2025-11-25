package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.restaurant.entity.QRegion;
import com.example.umc9th.domain.restaurant.entity.QRestaurant;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.QReply;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    //리뷰 검색
    @Override
    public List<Review> searchReview(Predicate predicate) {
        // Q 클래스 선언
        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;
        QRegion region = QRegion.region;

        return queryFactory
                .selectFrom(review)
                .leftJoin(restaurant).on(restaurant.id.eq(review.restaurant.id))
                .leftJoin(region).on(region.id.eq(restaurant.region.id))
                .where(predicate)
                .fetch();
    }

    //내가 작성한 리뷰
    @Override
    public List<MyReviewResponse> searchMyReviews(Predicate predicate) {
        // Q 클래스 선언
        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;
        QReply reply = QReply.reply;

        return queryFactory
                .select(Projections.constructor(
                        MyReviewResponse.class,
                        review.id,
                        review.content,
                        review.star,
                        reply.content
                ))
                .from(review)
                .leftJoin(review.restaurant, restaurant)
                .leftJoin(reply).on(reply.review.eq(review))
                .where(predicate)
                .orderBy(review.id.asc())
                .fetch();
    }
}
