package com.example.umc9th.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    public record ChallengeDTO(
            Long memberId,
            Long missionId,
            LocalDate deadline
    ) {}
}
