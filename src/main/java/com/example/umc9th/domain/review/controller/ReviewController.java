package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    // 리뷰 검색 API
    @GetMapping("/reviews/search")
    public ApiResponse<List<Review>> searchReview(@RequestParam String query,
                                                  @RequestParam String type){
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return ApiResponse.onSuccess(code, result);
    }

    // 내가 작성한 리뷰 보기 API
    @GetMapping("/reviews/my")
    public ApiResponse<List<MyReviewResponse>> searchMyReviews(@RequestParam Long memberId,
                                                               @RequestParam(required = false) String restaurantName,
                                                               @RequestParam(required = false) Integer star){
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        // 서비스에게 요청
        List<MyReviewResponse> result = reviewQueryService.searchMyReviews(memberId, restaurantName, star);
        return ApiResponse.onSuccess(code, result);
    }
}
