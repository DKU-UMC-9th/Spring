package com.example.umc_spring_first.domain.user.exception;

import com.example.umc_spring_first.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring_first.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException{
    public UserException(BaseErrorCode code) {
        super(code);
    }
}