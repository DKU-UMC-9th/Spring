package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    /**
     * 내가 작성한 리뷰 조회 API
     */
    @Operation(summary = "내가 작성한 리뷰 조회", description = "사용자가 작성한 리뷰를 필터링하여 페이징 조회합니다.")
    @GetMapping("/user/{userId}")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minStar,
            @RequestParam(required = false) Integer maxStar,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {

        Page<ReviewResponse> reviews = reviewQueryService.getReviewsByUserWithFilters(
                userId, storeId, minStar, maxStar, page
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

    // 가게의 리뷰 목록 조회
    @Operation(summary = "가게의 리뷰 목록 조회", description = "가게 이름으로 리뷰 리스트를 페이징하여 조회합니다.")
    @GetMapping
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewResDTO.ReviewPreviewListDTO result =
                reviewQueryService.findReview(storeName, page);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_FOUND,
                result
        );
    }
}
