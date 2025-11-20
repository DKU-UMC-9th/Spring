package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MISSION_200", "미션 조회 성공"),
    CREATE(HttpStatus.CREATED, "MISSION_201", "미션 도전 성공"),
    SUCCESS(HttpStatus.OK, "MISSION_200_S", "미션 성공 처리 완료");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
