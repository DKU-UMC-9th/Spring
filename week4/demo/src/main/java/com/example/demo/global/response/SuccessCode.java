// src/main/java/com/example/demo/global/response/SuccessCode.java
package com.example.demo.global.response;

import org.springframework.http.HttpStatus;

public enum SuccessCode {

    // === Review 도메인 관련 응답 코드 ===
    REVIEW_CREATE_SUCCESS(HttpStatus.OK,        "R001", "리뷰가 성공적으로 등록되었습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK,        "R002", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.NO_CONTENT,"R003", "리뷰가 성공적으로 삭제되었습니다."),
    REVIEW_MY_LIST_SUCCESS(HttpStatus.OK,       "R004", "내 리뷰 목록 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    SuccessCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
