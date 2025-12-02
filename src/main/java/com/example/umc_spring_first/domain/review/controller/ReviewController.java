package com.example.umc_spring_first.domain.review.controller;

import com.example.umc_spring_first.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_spring_first.domain.review.service.ReviewQueryService;
import com.example.umc_spring_first.domain.review.service.ReviewService;
import com.example.umc_spring_first.global.apiPayload.ApiResponse;
import com.example.umc_spring_first.global.apiPayload.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Validated   // @ValidPage 동작하게 해주는 어노테이션
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;

    // 전체 리뷰 조회
    @Operation(
            summary = "전체 리뷰 조회",
            description = "storeId, 별점(stars) 기준으로 필터링하여 페이지 단위(10개)로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효하지 않은 page 값입니다.")
    })
    @GetMapping
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false, name = "stars") Integer starBand,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.getReviews(storeId, starBand, page)
        );
    }

    // 내가 작성한 리뷰 조회
    @Operation(
            summary = "내가 작성한 리뷰 조회",
            description = "로그인 미구현 → userId=1 로 가정하고 내가 작성한 리뷰를 페이지 단위로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "리뷰 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효하지 않은 page 값입니다.")
    })
    @GetMapping("/my")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getMyReviews(
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                reviewQueryService.getMyReviews(1L, page)
        );
    }

    // 리뷰 생성
    @Operation(
            summary = "리뷰 생성",
            description = "별점(rating), 내용(content)을 입력받아 리뷰를 생성합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "리뷰 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "요청 데이터가 유효하지 않음")
    })
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReviewResponse> createReview(
            @RequestBody ReviewReqDTO.CreateReviewRequest req
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                reviewService.createReview(req)
        );
    }
}
