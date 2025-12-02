// src/main/java/com/example/umc_spring_first/domain/mission/dto/res/MissionResDTO.java
package com.example.umc_spring_first.domain.mission.dto.res;

import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 2번: 특정 가게의 미션 1개
    @Builder
    public record StoreMissionPreviewDTO(
            Long missionId,
            Integer point,
            String description
    ) {}

    // 2번: 특정 가게의 미션 리스트 + 페이징 정보
    @Builder
    public record StoreMissionPreviewListDTO(
            List<StoreMissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 3번: 내가 진행 중인 미션 1개
    @Builder
    public record MyMissionPreviewDTO(
            Long userMissionId,
            Integer point,
            String storeName,
            String description,
            UserMissionStatus status,
            LocalDateTime deadline
    ) {}

    // 3번: 내가 진행 중인 미션 리스트 + 페이징 정보
    @Builder
    public record MyMissionPreviewListDTO(
            List<MyMissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
