package com.example.umc_spring_first.domain.review.exception.code;

import com.example.umc_spring_first.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201_1",
            "성공적으로 리뷰를 생성했습니다."),

    FOUND(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
