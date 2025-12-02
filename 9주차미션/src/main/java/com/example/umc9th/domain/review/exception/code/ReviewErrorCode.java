package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    // Review 관련 에러
    INVALID_USER_ID(HttpStatus.BAD_REQUEST, "REVIEW400_1", "유효하지 않은 사용자 ID입니다."),
    INVALID_STAR_RANGE(HttpStatus.BAD_REQUEST, "REVIEW400_2", "별점은 1~5 사이여야 하며, 최소값이 최대값보다 클 수 없습니다."),

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    REVIEW_ACCESS_DENIED(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 리뷰에 접근할 권한이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
