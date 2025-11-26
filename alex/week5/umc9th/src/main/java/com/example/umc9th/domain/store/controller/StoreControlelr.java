// package com.example.umc9th.domain.store.controller;

// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.http.ResponseEntity;
// import jakarta.validation.Valid; 
// import org.springframework.web.bind.annotation.RequestBody;

// @RestController
// @RequestMapping("/api/v1/reviews")
// @RequiredArgsConstructor
// public class StoreControlelr {

//     private final ReviewService reviewService;

//     @PostMapping
//     public ApiResponse<ReviewResponse> Create(
//         @Valid @RequestBody ReviewRequestCreate request
//     ) {
//         ReviewResponse resp = reviewService.createReview(request);
//         return ApiResponse.onSuccess(GeneralSuccessCode.CREATE, resp);
//     }

    
// }