// src/main/java/com/example/demo/controller/ReviewController.java
package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewCreateRequest;
import com.example.demo.domain.review.dto.ReviewDto;
import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.service.ReviewService;
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
    public ResponseEntity<ReviewResponse> createReview(
            @RequestBody ReviewCreateRequest request
            ){
        ReviewResponse reviewResponse=service.createReview(request);
        return ResponseEntity.ok(reviewResponse);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewCreateRequest request
    ){
        ReviewResponse reviewResponse = service.updateReview(request.userId(), reviewId, request);
        return ResponseEntity.ok(reviewResponse);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long userId
    ) {
        service.deleteReview(reviewId, userId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/my")
    public List<ReviewDto> myReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String marketName,
            @RequestParam(required = false) Integer starBand
    ) {
        return service.findMyReviews(userId, marketName, starBand);
    }
}
