// src/main/java/com/example/demo/controller/ReviewController.java
package com.example.demo.controller;

import com.example.demo.dto.ReviewDto;
import com.example.demo.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    // GET /api/reviews/my?userId=1&marketName=반이학생마라탕마라반&starBand=4
    @GetMapping("/my")
    public List<ReviewDto> myReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) String marketName,
            @RequestParam(required = false) Integer starBand
    ) {
        return service.findMyReviews(userId, marketName, starBand);
    }
}
