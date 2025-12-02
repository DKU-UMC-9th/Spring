package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO.MemberMissionPreviewListDTO;

public interface MemberMissionQueryService {

    // 내가 진행중인 미션 목록
    MemberMissionPreviewListDTO findMyOngoingMissions(Long memberId, Integer page);
}
