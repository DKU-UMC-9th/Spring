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
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/search")
    public List<Review> Search(@RequestParam String query, @RequestParam String type) {
        List<Review> reviews = reviewService.searchReview(query, type);
        return reviews;
    }

    @GetMapping("/myreview")
    public List<Review> My(@RequestParam(required = false) String storeName,
                           @RequestParam(required = false) Integer star) {
        long memberId = 1;
        List<Review> reviews = reviewService.getMyReviews(memberId, storeName, star);
        return reviews;

    }
}