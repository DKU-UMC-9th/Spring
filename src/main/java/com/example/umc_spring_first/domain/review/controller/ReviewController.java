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

    /**
     * 하나의 API로 가게/별점대 필터를 모두 처리
     * 예)
     * /api/reviews/my?userId=10                -> 내가 쓴 전체
     * /api/reviews/my?userId=10&storeId=3      -> 특정 가게만
     * /api/reviews/my?userId=10&stars=4        -> 4.x만
     * /api/reviews/my?userId=10&storeId=3&stars=5 -> 그 가게의 5.0만
     */
    @GetMapping("/my")
    public Page<MyReviewRowDto> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false, name = "stars") Integer starBand,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return reviewQueryService.getMyReviews(userId, storeId, starBand, pageable);
    }
}
