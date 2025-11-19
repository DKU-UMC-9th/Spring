package com.example.demo.global.apipayload.response;
import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getHttpStatus();
    String getMessage();
    String getCode();
}
