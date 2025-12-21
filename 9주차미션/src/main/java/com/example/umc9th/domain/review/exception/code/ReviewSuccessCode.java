package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 작성되었습니다."),
    REVIEW_DELETED(HttpStatus.OK, "REVIEW200_1", "리뷰가 성공적으로 삭제되었습니다."),
    REVIEW_UPDATED(HttpStatus.OK, "REVIEW200_2", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_FOUND(HttpStatus.OK, "REVIEW200_3", "리뷰가 성공적으로 조회되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}