package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            LocalDate deadline
    ) {}
}
