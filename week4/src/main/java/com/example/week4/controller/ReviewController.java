package com.example.week4.controller;

import com.example.week4.apiPayload.ApiResponse;
import com.example.week4.apiPayload.code.GeneralSuccessCode;
import com.example.week4.domain.Review;
import com.example.week4.repository.ReviewRepository;
import com.example.week4.service.ReviewCommandService;
import com.example.week4.web.dto.ReviewRequestDTO;
import com.example.week4.web.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDto> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDto request
    ) {
        Review review = reviewCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.REVIEW_CREATED, ReviewResponseDTO.CreateReviewResultDto.from(review));
    }

    @GetMapping("/{storeId}/reviews")
    public ApiResponse<List<Review>> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(required = false) List<Integer> starRating,
            Pageable pageable
    ) {
        List<Review> reviewPage = reviewRepository.findReviewsByStoreAndFilter(storeId, starRating, pageable);

        return ApiResponse.onSuccess(GeneralSuccessCode.GET_SUCCESS, reviewPage);
    }
}