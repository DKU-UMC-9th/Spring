package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
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
    public List<Review> searchReview(@RequestParam String query,
                                     @RequestParam String type){
        // 서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }

    // 내가 작성한 리뷰 보기 API
    @GetMapping("/reviews/my")
    public List<MyReviewResponse> searchMyReviews(@RequestParam Long memberId,
                                                  @RequestParam(required = false) String restaurantName,
                                                  @RequestParam(required = false) Integer star){
        // 서비스에게 요청
        List<MyReviewResponse> result = reviewQueryService.searchMyReviews(memberId, restaurantName, star);
        return result;
    }
}
