package com.example.demo.global.apiPayload.Handler;

import com.example.demo.global.apiPayload.Exception.ReviewException;
import com.example.demo.global.apiPayload.response.ApiResponse;
import com.example.demo.global.apiPayload.response.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ReviewExceptionHandler {

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ApiResponse<Void>> handleReviewException(ReviewException e) {
        ErrorCode errorCode =e.getErrorCode();
        return ApiResponse.ERROR(errorCode);
    }


}
