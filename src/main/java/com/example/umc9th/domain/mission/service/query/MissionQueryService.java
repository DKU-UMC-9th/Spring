package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    // 특정 가게의 미션 목록 조회
    MissionResDTO.MissionPreviewListDTO findMissions(Long restaurantId, Integer page);
}
