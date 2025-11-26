package com.example.demo.domain.mission.exception.code;

import com.example.demo.global.apipayload.response.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_CREATED(HttpStatus.CREATED, "M001", "미션 생성에 성공했습니다."),
    MISSION_LIST(HttpStatus.OK, "M002", "미션 목록 조회에 성공했습니다."),
    MISSION_ACCEPTED(HttpStatus.OK, "M003", "미션을 수락했습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "M004", "미션을 완료했습니다."),
    MISSION_DETAIL(HttpStatus.OK, "M005", "미션 상태 조회에 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
