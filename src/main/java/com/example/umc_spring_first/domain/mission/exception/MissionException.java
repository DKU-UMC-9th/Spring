// src/main/java/com/example/umc_spring_first/domain/mission/exception/MissionException.java
package com.example.umc_spring_first.domain.mission.exception;

import com.example.umc_spring_first.global.apiPayload.code.BaseErrorCode;
import com.example.umc_spring_first.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
