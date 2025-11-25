package com.example.week4.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionResultDto {
        private Long missionId;
        private Long userId; // 혹은 userMissionId가 있으면 좋지만 복합키라 일단 userId 반환
        private LocalDateTime createdAt;

        public static ChallengeMissionResultDto from(com.example.week4.domain.UserMission userMission) {
            return ChallengeMissionResultDto.builder()
                    .missionId(userMission.getMission().getId())
                    .userId(userMission.getUser().getId())
                    .createdAt(java.time.LocalDateTime.now())
                    .build();
        }
    }
}
