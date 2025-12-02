package com.example.week4.converter;

import com.example.week4.domain.Mission;
import com.example.week4.domain.UserMission;
import com.example.week4.web.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionDto toMissionDto(Mission mission) {
        return MissionResponseDTO.MissionDto.builder()
                .missionId(mission.getId())
                .reward(mission.getAccumulation_point())
                .missionAmount(mission.getAccomplished_amount())
                .deadline(mission.getDeadline())
                .storeName(mission.getStore() != null ? mission.getStore().getName() : null)
                .build();
    }

    public static MissionResponseDTO.MissionDto toMissionDto(UserMission userMission) {
        return toMissionDto(userMission.getMission());
    }

    public static MissionResponseDTO.MissionListDto toMissionListDto(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionDto> missionDtoList = missionPage.stream()
                .map(MissionConverter::toMissionDto)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDto.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionDtoList.size())
                .missionList(missionDtoList)
                .build();
    }

    public static MissionResponseDTO.MissionListDto toMissionListDtoFromUserMission(Page<UserMission> userMissionPage) {
        List<MissionResponseDTO.MissionDto> missionDtoList = userMissionPage.stream()
                .map(MissionConverter::toMissionDto)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDto.builder()
                .isLast(userMissionPage.isLast())
                .isFirst(userMissionPage.isFirst())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .listSize(missionDtoList.size())
                .missionList(missionDtoList)
                .build();
    }
}
