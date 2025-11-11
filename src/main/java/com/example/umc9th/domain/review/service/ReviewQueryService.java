package com.example.umc9th.domain.review.service;

import static com.example.umc9th.domain.restaurant.entity.QRegion.region;

import com.example.umc9th.domain.restaurant.entity.QRestaurant;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type){
        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if(type.equals("region")){
            builder.and(region.name.contains(query));
        }
        if(type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")){

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(region.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return reviewList;
    }

    // 내가 작성한 리뷰
    public List<MyReviewResponse> searchMyReviews(Long memberId, String restaurantName, Integer star){
        // Q 클래스 선언
        QReview review = QReview.review;
        QRestaurant restaurant = QRestaurant.restaurant;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용
        builder.and(review.member.id.eq(memberId)); //내 리뷰만

        if(restaurantName != null && !restaurantName.isEmpty()){
            builder.and(review.restaurant.name.contains(restaurantName));
        }
        if(star != null){
            float minStar = star.floatValue();   // 4 → 4.0
            float maxStar = star + 0.9f;         // 4 → 4.9
            builder.and(review.star.between(minStar, maxStar));
        }

        // Repository 사용 & 결과 매핑
        List<MyReviewResponse> myReviews = reviewRepository.searchMyReviews(builder);

        // 리턴
        return myReviews;

    }

}
