package com.example.week4.web.dto;

import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class ChallengeMissionDto {
        private Long userId;
        private Long missionId;
    }
}
