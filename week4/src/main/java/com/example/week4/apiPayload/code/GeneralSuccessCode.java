package com.example.week4.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    // ====================== 200 OK ======================
    GET_SUCCESS(HttpStatus.OK, "COMMON20001", "데이터를 성공적으로 조회했습니다.");
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
