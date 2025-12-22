package com.example.umc_spring_first.domain.user.exception.code;

import com.example.umc_spring_first.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}