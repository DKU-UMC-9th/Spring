package com.example.demo.global.apipayload.Exception;

import com.example.demo.domain.review.exception.code.ReviewErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final ReviewErrorCode errorCode;

    public GeneralException(ReviewErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
