package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //테스트
    @GetMapping("/search")
    public List<Review> Search(@RequestParam String query, @RequestParam String type) { //query : "안암동", type:"location"
        List<Review> reviews = reviewService.searchReview(query, type);
        return reviews;
    }

    //미션 : 내가 직접 작성한 리뷰 조회 (가게명 / 별점 필터링)
    @GetMapping("/my")
    public List<Review> My(@RequestParam(required = false) String storeName,    //아무 값도 안 들어올 수 있음
                           @RequestParam(required = false) Integer star) {
        long memberId = 1;
        List<Review> reviews = reviewService.getMyReviews(memberId, storeName, star);
        return reviews;

    }




}
