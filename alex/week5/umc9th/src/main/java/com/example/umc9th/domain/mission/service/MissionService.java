package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.global.common.PageResponse;

public interface MissionService {
    PageResponse<MissionResponse> getStoreMissions(Long storeId, int page);
}
