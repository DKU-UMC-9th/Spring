package com.example.umc_spring_first.week7.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
