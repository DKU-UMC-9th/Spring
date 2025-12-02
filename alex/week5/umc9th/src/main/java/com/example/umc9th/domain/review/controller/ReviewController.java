package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.common.PageResponse;
import com.example.umc9th.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.domain.review.dto.ReviewRequestCreate;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성", description = "새로운 리뷰를 생성합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 생성 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/create")
    public ApiResponse<ReviewResponse> Create(
        @Parameter(description = "리뷰 생성 요청 데이터") @Valid @RequestBody ReviewRequestCreate request
    ) {
        ReviewResponse resp = reviewService.createReview(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATE, resp);
    }

    @Operation(summary = "가게별 리뷰 조회", description = "특정 가게의 리뷰 목록을 조회합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/search")
    public ApiResponse<List<ReviewResponse>> Search(
        @Parameter(description = "가게 ID", required = true) @RequestParam Long storeId
    ) {
        List<ReviewResponse> resp = reviewService.search(storeId);
        return ApiResponse.onSuccess(GeneralSuccessCode.GOOD_REQUEST, resp);
    }

    // 내가 작성한 리뷰 목록 조회
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "사용자가 작성한 리뷰 목록을 페이징하여 조회합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호")
    })
    @GetMapping("/my")
    public ApiResponse<PageResponse<ReviewResponse>> getMyReviews(
        @Parameter(description = "사용자 ID", required = true) @RequestParam Long userId,
        @Parameter(description = "페이지 번호 (1부터 시작)", required = true) @ValidPage @RequestParam int page
    ) {
        PageResponse<ReviewResponse> response = reviewService.getMyReviews(userId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.GOOD_REQUEST, response);
    }

}