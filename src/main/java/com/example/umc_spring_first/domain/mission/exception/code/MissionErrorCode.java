// src/main/java/com/example/umc_spring_first/domain/mission/exception/code/MissionErrorCode.java
package com.example.umc_spring_first.domain.mission.exception.code;

import com.example.umc_spring_first.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "가게를 찾을 수 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "미션을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
