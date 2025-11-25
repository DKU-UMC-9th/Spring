package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MemberMissionConverter {

    // Entity -> DTO
    public static MemberMissionResDTO.ChallengeResponse toChallengeRes(MemberMission memberMission){
        return MemberMissionResDTO.ChallengeResponse.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .complete(memberMission.isComplete())
                .build();
    }

    // DTO -> Entity
    public static MemberMission toMemberMission(Member member, Mission mission){
        return MemberMission.create(member, mission);
    }

}
