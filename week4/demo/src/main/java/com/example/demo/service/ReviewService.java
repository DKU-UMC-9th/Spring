package com.example.demo.service;

import com.example.demo.dto.ReviewDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;

import static com.example.demo.domain.restruant.QFoodMarket.foodMarket;
import static com.example.demo.domain.review.QReview.review;

@Service
public class ReviewService  {
    private final JPAQueryFactory qf;

    public ReviewService(JPAQueryFactory qf) { this.qf = qf; }

    public List<ReviewDto> findMyReviews(Long userId, String marketName, Integer starBand) {
        // userId should be required; validate if needed
        return qf.select(Projections.constructor(ReviewDto.class,
                        review.id,
                        review.market.name,
                        review.content,
                        review.star))
                .from(review)
                .join(review.market, foodMarket)
                .where(
                        review.user.id.eq(userId),
                        review.market.name.eq(marketName),
                        starBandPredicate(starBand)
                )
                .orderBy(review.id.desc())
                .fetch();
    }


    private BooleanExpression starBandPredicate(Integer band) {
        if (band == null) return null;
        if (band == 5) return review.star.eq(new BigDecimal("5.0"));
        if (band >= 0 && band <= 4) {
            BigDecimal min = new BigDecimal(band + ".0");
            BigDecimal max = new BigDecimal((band + 1) + ".0");
            return review.star.goe(min).and(review.star.lt(max));
        }
        return null;
    }
}

