package com.example.umc_spring_first.domain.user.exception.code;

import com.example.umc_spring_first.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD404_1", "음식을 찾을 수 없습니다."),
    INVALID_FOOD_ID(HttpStatus.BAD_REQUEST, "FOOD400_1", "유효하지 않은 음식 ID입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}