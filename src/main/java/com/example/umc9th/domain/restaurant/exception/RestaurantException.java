package com.example.umc9th.domain.restaurant.exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class RestaurantException extends GeneralException {
    public RestaurantException(BaseErrorCode code) {
        super(code);
    }
}
