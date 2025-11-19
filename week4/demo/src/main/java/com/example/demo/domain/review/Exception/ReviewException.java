package com.example.demo.domain.review.exception;

import com.example.demo.global.apipayload.Exception.GeneralException;
import com.example.demo.domain.review.exception.code.ReviewErrorCode;

public class ReviewException extends GeneralException {
    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
