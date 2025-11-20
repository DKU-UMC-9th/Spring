package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;

import java.time.LocalDate;

public class MissionConverter {

    // UserMission 생성 (도전하기)
    public static UserMission toUserMission(Member member, Mission mission, LocalDate deadline) {
        return UserMission.builder()
                .member(member)
                .mission(mission)
                .deadline(deadline)
                .status(true)   // 도전 시작 → 진행중
                .build();
    }

    // 도전 응답 DTO
    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission userMission) {
        return MissionResDTO.ChallengeDTO.builder()
                .userMissionId(userMission.getId())
                .deadline(userMission.getDeadline())
                .build();
    }

    // 내 미션 목록 DTO
    public static MissionResDTO.MyMissionDTO toMyMissionDTO(UserMission um) {
        return MissionResDTO.MyMissionDTO.builder()
                .userMissionId(um.getId())
                .missionId(um.getMission().getId())
                .missionStatus(um.isStatus() ? "CHALLENGING" : "SUCCESS")
                .point(um.getMission().getPoint())
                .storeName(um.getMission().getStore().getName())
                .deadline(um.getDeadline())
                .build();
    }

    // 미션 성공 응답 DTO
    public static MissionResDTO.SuccessDTO toSuccessDTO(UserMission um) {
        return MissionResDTO.SuccessDTO.builder()
                .userMissionId(um.getId())
                .missionStatus("SUCCESS")
                .build();
    }
}
