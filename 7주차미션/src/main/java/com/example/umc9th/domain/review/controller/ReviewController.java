package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    /**
     * 내가 작성한 리뷰 조회 API (필터링 포함)
     * <p>
     * 사용 예시:
     * - 전체 조회: GET /reviews/user/1
     * - 가게별: GET /reviews/user/1?storeId=5
     * - 별점 범위: GET /reviews/user/1?minStar=3&maxStar=5
     * - 조합: GET /reviews/user/1?storeId=5&minStar=4&maxStar=5
     */
    @GetMapping("/user/{userId}")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer minStar,
            @RequestParam(required = false) Integer maxStar
    ) {
        // Service 호출
        List<ReviewResponse> reviews = reviewQueryService.getReviewsByUserWithFilters(
                userId, storeId, minStar, maxStar
        );

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // Converter를 통해 DTO 변환 후 응답
        return ApiResponse.onSuccess(
                code,
                ReviewConverter.toReviewListDTO(reviews)
        );
    }
}