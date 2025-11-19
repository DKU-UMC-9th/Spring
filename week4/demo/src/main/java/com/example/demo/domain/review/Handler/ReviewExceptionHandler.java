package com.example.demo.domain.review.Handler;

import com.example.demo.domain.review.Exception.ReviewException;
import com.example.demo.global.apiPayload.response.ApiResponse;
import com.example.demo.domain.review.Exception.code.ReviewErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ReviewExceptionHandler {

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ApiResponse<Void>> handleReviewException(ReviewException e) {
        ReviewErrorCode errorCode =e.getErrorCode();
        return ApiResponse.ERROR(errorCode);
    }


}
