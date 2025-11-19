package com.example.demo.domain.review.Exception;

import com.example.demo.global.apiPayload.Exception.GeneralException;
import com.example.demo.domain.review.Exception.code.ReviewErrorCode;

public class ReviewException extends GeneralException {
    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode);
    }
}
