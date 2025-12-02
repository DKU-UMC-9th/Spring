package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO.CreateReviewRequest;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.CreateReviewResponse;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.ReviewPreviewListDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController{

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

//    // 내가 작성한 리뷰 보기 API(가게별, 별점별 필터링)
//    @GetMapping("/reviews/my")
//    public ApiResponse<List<ReviewResDTO.MyReviewResponse>> searchMyReviews(@RequestParam Long memberId,
//                                                                            @RequestParam(required = false) String restaurantName,
//                                                                            @RequestParam(required = false) Integer star){
//
//        // 서비스에게 요청
//        List<MyReviewResponse> result = reviewQueryService.searchMyReviews(memberId, restaurantName, star);
//        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, result);
//    }

    // 내가 작성한 리뷰 목록 API (9주차 미션)
    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "memberId 기준으로 내가 작성한 리뷰를 page 단위(1페이지당 10개)로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 리뷰를 조회했습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효하지 않은 page 값입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원 또는 리뷰를 찾을 수 없습니다.")
    })
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam Long memberId,
            @RequestParam @ValidPage Integer page   // 페이지 번호
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReview(memberId, page));
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
    @GetMapping("/reviews/restaurant")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getReviews(
            @RequestParam String restaurantName,
            @RequestParam Integer page   // 페이지 번호
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(restaurantName, page));
    }



}
