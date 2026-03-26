package com.example.demo.domain.mission.exception;

import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import com.example.demo.global.apipayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(MissionErrorCode errorCode) {
        super(errorCode);
    }
}
