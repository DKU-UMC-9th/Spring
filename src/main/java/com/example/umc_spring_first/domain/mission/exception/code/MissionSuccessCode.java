package com.example.umc_spring_first.domain.mission.exception.code;

import com.example.umc_spring_first.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션을 생성했습니다."),

    CHALLENGED(HttpStatus.CREATED,
            "MISSION201_2",
            "성공적으로 미션 도전을 등록했습니다."),

    FOUND(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
