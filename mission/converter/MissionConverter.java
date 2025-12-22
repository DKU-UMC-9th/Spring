package com.example.umc_spring_first.domain.mission.converter;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    // 미션 생성 응답
    public static MissionResDTO.CreateMissionResponse toCreateMissionResponse(Mission mission) {
        return MissionResDTO.CreateMissionResponse.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreateAt())
                .build();
    }

    // 미션 목록 페이지 -> DTO
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<MissionResDTO.MissionPreviewDTO> page
    ) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(page.getContent().stream().toList()) // Stream 사용
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
