package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    /**
     * 내가 작성한 리뷰 조회 API
     */
    @GetMapping("/user/{userId}")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minStar,
            @RequestParam(required = false) Integer maxStar
    ) {
        List<ReviewResponse> reviews = reviewQueryService.getReviewsByUserWithFilters(
                userId, storeId, minStar, maxStar
        );
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                ReviewConverter.toReviewListDTO(reviews)
        );
    }

    /**
     * 2. 가게에 리뷰 추가하기 API
     * POST /reviews
     */
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        ReviewResDTO.CreateReviewResultDTO result = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}