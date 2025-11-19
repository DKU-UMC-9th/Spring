// src/main/java/com/example/demo/controller/ReviewController.java
package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewCreateRequest;
import com.example.demo.domain.review.dto.ReviewDto;
import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.service.ReviewService;
import com.example.demo.global.apiPayload.response.ApiResponse;
import com.example.demo.global.apiPayload.response.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;


    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
            @RequestBody ReviewCreateRequest request
            ){
        ReviewResponse reviewResponse=service.createReview(request);
        return ApiResponse.success(SuccessCode.REVIEW_CREATE_SUCCESS,reviewResponse);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewCreateRequest request
    ){
        ReviewResponse reviewResponse = service.updateReview(request.userId(), reviewId, request);
        return ApiResponse.success(SuccessCode.REVIEW_UPDATE_SUCCESS,reviewResponse);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long userId
    ) {
        service.deleteReview(reviewId, userId);
        return ApiResponse.success(SuccessCode.REVIEW_DELETE_SUCCESS);
    }
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ReviewDto>>> myReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String marketName,
            @RequestParam(required = false) Integer starBand
    ) {
        List<ReviewDto> result = service.findMyReviews(userId, marketName, starBand);
        return ApiResponse.success(SuccessCode.REVIEW_MY_LIST_SUCCESS, result);
    }
}
