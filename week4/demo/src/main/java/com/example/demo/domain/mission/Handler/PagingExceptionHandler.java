package com.example.demo.domain.mission.Handler;

import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.global.apipayload.response.ApiResponse;
import com.example.demo.global.apipayload.response.BaseErrorCode;


@RestControllerAdvice
public class PagingExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handlePagingException(ConstraintViolationException e) {

        BaseErrorCode errorCode = MissionErrorCode.INVALID_PAGE;
        return ApiResponse.ERROR(errorCode);
    }
}
