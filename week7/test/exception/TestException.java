package com.example.umc_spring_first.week7.test.exception;

import com.example.umc_spring_first.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring_first.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}

