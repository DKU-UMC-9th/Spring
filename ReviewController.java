package com.koo.week7.controller; 

import com.koo.week7.entity.Review; 
import com.koo.week7.dto.ReviewResponseDto; 
import com.koo.week7.repository.ReviewQueryRepository; 
import com.koo.week7.global.apiPayload.ApiResponse;
import com.koo.week7.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewRepository reviewRepository; 

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