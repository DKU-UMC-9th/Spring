package com.example.demo.global.apiPayload.response;

import org.springframework.http.HttpStatus;

import java.net.http.HttpRequest;

public interface BaseErrorCode {
    HttpStatus getHttpStatus();
    String getMessage();
    String getCode();
}
