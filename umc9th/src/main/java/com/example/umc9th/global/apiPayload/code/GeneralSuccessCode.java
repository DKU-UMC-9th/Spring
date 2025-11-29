package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    SUCCESS(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),
    REVIEW_LIST_SUCCESS(HttpStatus.OK, "REVIEW200", "내가 작성한 리뷰 조회"),
   ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}