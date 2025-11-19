package com.example.demo.global.apiPayload.response;
import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getHttpStatus();
    String getMessage();
    String getCode();
}
