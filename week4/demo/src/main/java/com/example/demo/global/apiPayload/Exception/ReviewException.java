package com.example.demo.global.apiPayload.Exception;

import com.example.demo.global.apiPayload.response.ErrorCode;
import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException {

    private final ErrorCode errorCode;

    public ReviewException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
