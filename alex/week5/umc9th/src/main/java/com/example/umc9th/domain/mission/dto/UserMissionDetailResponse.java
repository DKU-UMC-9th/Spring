package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record UserMissionDetailResponse(
    Long userMissionId,
    Long userId,
    Long missionId,
    String missionContent,
    Long storeId,
    String storeName,
    LocalDate deadline,
    Long point,
    Boolean isComplete,
    LocalDateTime completeAt
) {}
