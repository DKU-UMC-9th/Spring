package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO.CreateReviewRequest;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.CreateReviewResponse;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 리뷰 검색 API
    @GetMapping("/reviews/search")
    public ApiResponse<List<Review>> searchReview(@RequestParam String query,
                                                  @RequestParam String type){

        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
    }

    // 내가 작성한 리뷰 보기 API
    @GetMapping("/reviews/my")
    public ApiResponse<List<ReviewResDTO.MyReviewResponse>> searchMyReviews(@RequestParam Long memberId,
                                                                            @RequestParam(required = false) String restaurantName,
                                                                            @RequestParam(required = false) Integer star){

        // 서비스에게 요청
        List<MyReviewResponse> result = reviewQueryService.searchMyReviews(memberId, restaurantName, star);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
    }

    // 가게에 리뷰 추가하기 API
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResponse> createReview(
            @PathVariable Long restaurantId,
            @RequestBody CreateReviewRequest request
            ){

        CreateReviewResponse response = reviewCommandService.createReview(restaurantId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, response);
    }

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam String restaurantName,
            @RequestParam Integer page   // 페이지 번호
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(restaurantName, page));
    }

}
