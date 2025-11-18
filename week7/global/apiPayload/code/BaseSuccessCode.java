package com.example.umc_spring_first.week7.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode { //BaseErrorCode와 구조를 완전히 똑같이 유지하는 게 핵심

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
