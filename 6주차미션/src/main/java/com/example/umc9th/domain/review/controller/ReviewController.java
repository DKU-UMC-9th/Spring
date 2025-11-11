package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/user/{userId}")
    public List<ReviewResponse> getReviewsByUser( // 별점 필터링
            @PathVariable Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minStar,
            @RequestParam(required = false) Integer maxStar
    ) {
        return reviewService.getReviewsByUserWithFilters(userId, storeId, minStar, maxStar);
    }
}
