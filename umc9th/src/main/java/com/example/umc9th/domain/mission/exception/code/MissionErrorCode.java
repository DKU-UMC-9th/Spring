package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    // 미션 조회 오류
    NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_404", "해당 미션을 찾을 수 없습니다."),

    // 이미 도전 중
    ALREADY_CHALLENGING(HttpStatus.CONFLICT, "MISSION_409", "이미 도전 중인 미션입니다."),

    // 유저 미션 조회 오류
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_404_U", "해당 유저의 미션 정보를 찾을 수 없습니다."),

    // 미션 비활성화 상태
    MISSION_DISABLED(HttpStatus.BAD_REQUEST, "MISSION_400", "활성화되지 않은 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
