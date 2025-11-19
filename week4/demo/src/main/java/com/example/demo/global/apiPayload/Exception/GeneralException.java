package com.example.demo.global.apiPayload.Exception;

import com.example.demo.domain.review.Exception.code.ReviewErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final ReviewErrorCode errorCode;

    public GeneralException(ReviewErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
