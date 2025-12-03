package com.example.demo.domain.review.exception;

import com.example.demo.global.apipayload.exception.GeneralException;
import com.example.demo.domain.review.exception.code.ReviewErrorCode;

public class ReviewException extends GeneralException {
    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
