package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

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

    public static MemberMissionResDTO.MemberMissionPreviewListDTO toMemberMissionPreviewListDTO(
            Page<MemberMission> result
    ){
        return MemberMissionResDTO.MemberMissionPreviewListDTO.builder()
                .memberMissionList(result.getContent().stream()
                        .map(MemberMissionConverter::toMemberMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionPreviewDTO toMemberMissionPreviewDTO(
            MemberMission memberMission
    ){
        Mission mission = memberMission.getMission();

        return MemberMissionResDTO.MemberMissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .restaurantName(mission.getRestaurant().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .expiredAt(mission.getExpiredAt())
                .complete(memberMission.isComplete())
                .build();
    }

}
