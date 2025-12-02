// src/main/java/com/example/umc_spring_first/domain/mission/converter/MissionConverter.java
package com.example.umc_spring_first.domain.mission.converter;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import org.springframework.data.domain.Page;

public class MissionConverter {

    // 2번: 특정 가게 미션 리스트
    public static MissionResDTO.StoreMissionPreviewListDTO toStoreMissionListDTO(
            Page<MissionResDTO.StoreMissionPreviewDTO> page
    ) {
        return MissionResDTO.StoreMissionPreviewListDTO.builder()
                .missionList(page.getContent())   // 이미 DTO라 stream 필요 없음
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // 3번: 내가 진행중인 미션 리스트
    public static MissionResDTO.MyMissionPreviewListDTO toMyMissionListDTO(
            Page<MissionResDTO.MyMissionPreviewDTO> page
    ) {
        return MissionResDTO.MyMissionPreviewListDTO.builder()
                .missionList(page.getContent())
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
