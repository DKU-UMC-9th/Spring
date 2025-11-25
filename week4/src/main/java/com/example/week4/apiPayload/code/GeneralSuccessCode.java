package com.example.week4.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    // ====================== 200 OK ======================
    GET_SUCCESS(HttpStatus.OK, "COMMON20001", "데이터를 성공적으로 조회했습니다."),
    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW2001", "리뷰가 성공적으로 등록되었습니다."),
    OK(HttpStatus.OK, "COMMON200", "요청에 성공하였습니다."),
    MISSION_CHALLENGED(HttpStatus.CREATED, "MISSION2001", "미션 도전에 성공하였습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
