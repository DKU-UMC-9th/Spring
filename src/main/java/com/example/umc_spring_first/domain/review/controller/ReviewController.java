package com.example.umc_spring_first.domain.review.controller;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import com.example.umc_spring_first.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    /**
     * /api/reviews?storeId=1
     * /api/reviews?stars=4
     * /api/reviews?storeId=1&stars=4
     */
    @GetMapping
    public Page<MyReviewRowDto> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false, name = "stars") Integer starBand,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return reviewQueryService.getReviews(storeId, starBand, pageable);
    }
}
