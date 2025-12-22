package com.example.umc_spring_first.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 생성 응답 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateMissionResponse {
        private Long missionId;
        private LocalDateTime createdAt;
    }

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            Integer point,
            String description
    ) {}
}
