package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.annotation.PositivePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 테스트 검색
    @GetMapping("/search")
    public ApiResponse<?> search(@RequestParam String query, @RequestParam String type) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.SUCCESS,
                reviewService.searchReview(query, type)
        );
    }

    // 9주차 미션 — 내가 작성한 리뷰(페이징)(기존 Review 리스트 반환에서 Dto 반황으로 변경)
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.MyReviewPageDTO> myReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            @PositivePage @RequestParam int page
    ) {
        long memberId = 1; // 로그인 생략

        Pageable pageable = PageRequest.of(page - 1, 10);

        ReviewResDTO.MyReviewPageDTO result =
                reviewService.getMyReviews(memberId, storeName, star, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.SUCCESS, result);
    }

    // 리뷰 생성
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @RequestBody ReviewReqDTO.CreateDTO dto
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATE,
                reviewService.createReview(dto)
        );
    }
}
