package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewService;
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
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponse> Create(
        @Valid @RequestBody ReviewRequestCreate request
    ) {
        ReviewResponse resp = reviewService.createReview(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATE, resp);
    }

    // @GetMapping
    // public ApiResponse<ReviewResponse> Search(
    //     @RequestParam Long userId,
    //     @RequestParam Long storeId
    // ) {
    //     ReviewResponse resp = reviewService.findbyStore();
    //     return ApiResponse.onSuccess(GeneralSuccessCode.GOOD_REQUEST, resp);
    // }

}