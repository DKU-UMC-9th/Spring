package com.example.umc_spring_first.domain.review.exception;
import com.example.umc_spring_first.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring_first.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}

