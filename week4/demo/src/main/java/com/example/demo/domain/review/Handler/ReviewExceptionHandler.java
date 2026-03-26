package com.example.demo.domain.review.handler;

import com.example.demo.domain.review.exception.ReviewException;
import com.example.demo.global.apipayload.response.ApiResponse;
import com.example.demo.domain.review.exception.code.ReviewErrorCode;
import com.example.demo.global.apipayload.response.BaseErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ReviewExceptionHandler {

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ApiResponse<Void>> handleReviewException(ReviewException e) {
        BaseErrorCode errorCode =e.getErrorCode();
        return ApiResponse.ERROR(errorCode);
    }


}
