package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "가게를 찾을 수 없습니다."),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_2", "지역을 찾을 수 없습니다."),
    INVALID_STORE_ID(HttpStatus.BAD_REQUEST, "STORE400_1", "유효하지 않은 가게 ID입니다."),
    MISSION_NOT_FOUND_IN_STORE(HttpStatus.NOT_FOUND, "STORE404_3", "가게에 해당 미션이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}