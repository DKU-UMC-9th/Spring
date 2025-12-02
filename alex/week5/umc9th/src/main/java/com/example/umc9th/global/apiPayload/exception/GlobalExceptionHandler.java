package com.example.umc9th.global.apiPayload.exception;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(GeneralException e) {

        BaseErrorCode errorCode = e.getErrorCode();

        log.warn("[GeneralException] {} - {}", errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException e) {

        log.warn("[IllegalArgumentException] {}", e.getMessage());

        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST, null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolation(ConstraintViolationException e) {

        log.warn("[ConstraintViolationException] {}", e.getMessage());

        return ResponseEntity
                .status(GeneralErrorCode.INVALID_PAGE.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.INVALID_PAGE, null));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiResponse<?>> handleHandlerMethodValidation(HandlerMethodValidationException e) {

        log.warn("[HandlerMethodValidationException] {}", e.getMessage());

        return ResponseEntity
                .status(GeneralErrorCode.INVALID_PAGE.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.INVALID_PAGE, null));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse<?>> handleSecurityException(SecurityException e) {

        log.warn("[SecurityException] {}", e.getMessage());

        return ResponseEntity
                .status(GeneralErrorCode.UNAUTHORIZED.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.UNAUTHORIZED, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnexpectedException(Exception e) {

        log.error("[UnexpectedException]", e);

        return ResponseEntity
                .status(GeneralErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.INTERNAL_SERVER_ERROR, null));
    }
}