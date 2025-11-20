package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "INTEREST200_1",
            "성공적으로 미션을 조회했습니다."),

    CREATE(HttpStatus.CREATED,
            "REVIEW200_2",
            "미션 추가 성공")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}