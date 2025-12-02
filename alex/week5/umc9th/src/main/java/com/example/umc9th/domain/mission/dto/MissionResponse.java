package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MissionResponse(
    Long missionId,
    Long storeId,
    String storeName,
    String content,
    LocalDate deadline,
    Long point
) {}
