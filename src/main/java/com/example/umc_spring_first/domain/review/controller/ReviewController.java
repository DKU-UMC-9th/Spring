package com.example.umc_spring_first.domain.review.controller;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import com.example.umc_spring_first.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public Page<MyReviewRowDto> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false, name = "stars") Integer starBand,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return reviewQueryService.getMyReviews(userId, storeId, starBand, pageable);
    }

    @GetMapping("/store")
    public Page<MyReviewRowDto> getStoreReviews(
            @RequestParam Long storeId,
            @RequestParam(required = false, name = "stars") Integer starBand,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return reviewQueryService.getStoreReviews(storeId, starBand, pageable);
    }

    @GetMapping("/stars")
    public Page<MyReviewRowDto> getReviewsByStar(
            @RequestParam Integer stars,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return reviewQueryService.getReviewsByStar(stars, pageable);
    }
}
