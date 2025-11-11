package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public String queryTest(String name) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (name != null) {
            builder.and(review.user.name.eq(name));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList.toString();
    }

    public List<Review> searchReview(String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if ("location".equals(type)) {
            builder.and(review.store.addr.contains(query));
        } else if ("star".equals(type)) {
            builder.and(review.star.goe(Integer.parseInt(query)));
        } else if ("both".equals(type)) {
            String[] parts = query.split("&");
            builder.and(review.store.addr.contains(parts[0]));
            builder.and(review.star.goe(Integer.parseInt(parts[1])));
        }

        return reviewRepository.searchReview(builder);
    }

    public List<Review> getMyReviews(long userId, String storeName, Integer star) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.user.id.eq(userId));


        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        if (star != null) {
            builder.and(review.star.goe(star));
        }

        return reviewRepository.searchReview(builder);
    }
}
