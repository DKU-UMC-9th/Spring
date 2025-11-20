package com.example.umc9th.domain.member.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class InterestException extends GeneralException {
    public InterestException(BaseErrorCode code) {
        super(code);
    }
}