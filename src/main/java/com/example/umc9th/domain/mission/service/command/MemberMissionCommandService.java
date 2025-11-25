package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {

    // 미션 도전하기
    MemberMissionResDTO.ChallengeResponse challenge(Long missionId);
}
