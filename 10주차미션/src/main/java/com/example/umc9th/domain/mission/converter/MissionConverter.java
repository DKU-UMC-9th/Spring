package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.mapping.UserMission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTOFromUserMission(Page<UserMission> userMissionPage) {

        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(
                        userMissionPage.getContent().stream()
                                .map(um -> {
                                    Mission m = um.getMission();
                                    return MissionResDTO.MissionPreviewDTO.builder()
                                            .missionId(m.getId())
                                            .title(m.getTitle())
                                            .description(m.getDescription())
                                            .rewardPoint(m.getPoint())
                                            .build();
                                }).toList()
                )
                .currentPage(userMissionPage.getNumber() + 1)
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .build();
    }

    // Mission 전용
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTOFromMission(Page<Mission> missionPage) {

        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(
                        missionPage.getContent().stream()
                                .map(m ->
                                        MissionResDTO.MissionPreviewDTO.builder()
                                                .missionId(m.getId())
                                                .title(m.getTitle())
                                                .description(m.getDescription())
                                                .rewardPoint(m.getPoint())
                                                .build()
                                ).toList()
                )
                .currentPage(missionPage.getNumber() + 1)
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
