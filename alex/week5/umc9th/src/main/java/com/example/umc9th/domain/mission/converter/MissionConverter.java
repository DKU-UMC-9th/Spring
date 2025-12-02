package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionConverter {

    public MissionResponse toResponse(Mission mission) {
        return MissionResponse.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .deadline(mission.getDeadline())
                .point(mission.getPoint())
                .build();
    }
}
