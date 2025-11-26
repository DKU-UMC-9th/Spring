package com.example.umc9th.domain.mission.dto;

import java.time.LocalDateTime;

public record UserMissionResponse (
    Long userId,
    Long missionId,
    Boolean isComplete,
    LocalDateTime completeAt
){}
