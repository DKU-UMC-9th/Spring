package com.example.demo.domain.review.exception.code;

import com.example.demo.global.apipayload.response.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_CREATE_ERROR(HttpStatus.BAD_REQUEST,        "R001", "리뷰 등록을 실패했습니다."),
    REVIEW_UPDATE_ERROR(HttpStatus.BAD_REQUEST,        "R002", "리뷰 수정이 실패했습니다."),
    REVIEW_DELETE_ERROR(HttpStatus.BAD_REQUEST,"R003", "리뷰가 삭제에 실패했습니다."),
    REVIEW_MY_LIST_ERROR(HttpStatus.BAD_REQUEST,       "R004", "내 리뷰 목록 조회에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
