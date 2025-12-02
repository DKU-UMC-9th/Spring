package com.example.demo.domain.mission.exception.code;

import com.example.demo.global.apipayload.response.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "M4001", "존재하지 않는 미션입니다."),
    MARKET_NOT_FOUND(HttpStatus.NOT_FOUND, "M4002", "존재하지 않는 가게입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "M4003", "존재하지 않는 유저입니다."),
    INVALID_PAGE(HttpStatus.BAD_REQUEST,"P001", "페이지를 1이상으로 해라"),
    MISSION_ALREADY_ACCEPTED(HttpStatus.CONFLICT, "M4004", "이미 수락한 미션입니다."),
    MISSION_NOT_ACCEPTED(HttpStatus.BAD_REQUEST, "M4005", "아직 이 미션을 수락하지 않았습니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "M4006", "이미 완료된 미션입니다."),
    MISSION_CONDITION_NOT_MET(HttpStatus.BAD_REQUEST, "M4007", "미션 조건을 만족하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
