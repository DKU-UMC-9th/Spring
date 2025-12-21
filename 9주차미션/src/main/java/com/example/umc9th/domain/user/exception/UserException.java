package com.example.umc9th.domain.user.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class UserException extends GeneralException{
    public UserException(BaseErrorCode code) {
        super(code);
    }
}