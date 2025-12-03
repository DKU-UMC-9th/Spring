package com.example.umc_spring_first.domain.mission.converter;

import com.example.umc_spring_first.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.user.entity.User;
import org.springframework.data.domain.Page;

public class MissionConverter {

    // DTO → Entity
    public static Mission toMissionEntity(MissionReqDTO.CreateMissionRequest req, Store store) {
        return Mission.builder()
                .store(store)
                .point(req.getPoint())
                .description(req.getDescription())
                .build();
    }

    public static UserMission toUserMissionEntity(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMissionStatus.IN_PROGRESS)
                .build();
    }

    // Entity → DTO
    public static MissionResDTO.CreateMissionResponse toCreateMissionResponse(Mission mission) {
        return MissionResDTO.CreateMissionResponse.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreateAt())
                .build();
    }

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(UserMission um) {
        Mission mission = um.getMission();

        return MissionResDTO.MissionPreviewDTO.builder()
                .userMissionId(um.getId())
                .storeName(mission.getStore().getName())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .status(um.getStatus())
                .deadline(um.getDeadline())
                .build();
    }

    // Page → DTO
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewList(Page<UserMission> page) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(
                        page.getContent().stream()
                                .map(MissionConverter::toMissionPreviewDTO)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // Mission 목록 조회용
    public static MissionResDTO.MissionPreviewListDTO toStoreMissionList(Page<Mission> page) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(
                        page.getContent().stream()
                                .map(m -> MissionResDTO.MissionPreviewDTO.builder()
                                        .userMissionId(null)  // store mission 목록은 UserMission 없음
                                        .storeName(m.getStore().getName())
                                        .description(m.getDescription())
                                        .point(m.getPoint())
                                        .status(null)
                                        .deadline(null)
                                        .build()
                                )
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
