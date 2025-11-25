package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    CHALLENGED(HttpStatus.OK,
            "MEMBER_MISSION200_1",
            "미션 도전이 성공적으로 완료되었습니다."),

    FOUND(HttpStatus.OK,
            "MEMBER_MISSION200_2",
            "성공적으로 사용자 미션을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
