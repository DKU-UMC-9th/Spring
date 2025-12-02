package com.example.umc9th.global.apiPayload.handler;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(GeneralException ex) {

        BaseErrorCode errorCode = ex.getErrorCode();

        // 추가 로깅이나 모니터링 훅도 가능
        // log.warn("[GeneralException] {} - {}", errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ApiResponse<?>> handleBadRequest(RuntimeException ex) {
        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnexpectedException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}
