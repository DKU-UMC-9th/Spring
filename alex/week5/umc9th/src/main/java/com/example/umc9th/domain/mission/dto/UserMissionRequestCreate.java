package com.example.umc9th.domain.mission.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserMissionRequestCreate (

    @NotNull @Min(1)
    Long userId,
    @NotNull @Min(1)
    Long missionId,
    @NotNull
    Boolean isComplete,
    @NotNull
    LocalDateTime completeAt
) {}

