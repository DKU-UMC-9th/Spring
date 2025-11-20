package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;

import java.time.LocalDate;

public class MissionConverter {

    public static UserMission toUserMission(Member member, Mission mission, LocalDate deadline) {
        return UserMission.builder()
                .member(member)
                .mission(mission)
                .deadline(deadline)
                .status(true) // 도전중
                .build();
    }

    public static MissionResDTO.ChallengeDTO toChallengeDTO(UserMission userMission) {
        return MissionResDTO.ChallengeDTO.builder()
                .userMissionId(userMission.getId())
                .deadline(userMission.getDeadline())
                .build();
    }
}
