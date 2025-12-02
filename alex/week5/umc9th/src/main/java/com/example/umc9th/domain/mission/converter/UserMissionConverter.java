package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.UserMissionDetailResponse;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.stereotype.Component;

@Component
public class UserMissionConverter {

    public UserMissionDetailResponse toDetailResponse(UserMission userMission) {
        return UserMissionDetailResponse.builder()
                .userMissionId(userMission.getId())
                .userId(userMission.getUser().getId())
                .missionId(userMission.getMission().getId())
                .missionContent(userMission.getMission().getContent())
                .storeId(userMission.getMission().getStore().getId())
                .storeName(userMission.getMission().getStore().getName())
                .deadline(userMission.getMission().getDeadline())
                .point(userMission.getMission().getPoint())
                .isComplete(userMission.getIsComplete())
                .completeAt(userMission.getCompleteAt())
                .build();
    }
}
