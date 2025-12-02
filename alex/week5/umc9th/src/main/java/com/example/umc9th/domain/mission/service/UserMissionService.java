package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.UserMissionDetailResponse;
import com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.domain.mission.dto.UserMissionRequestCreate;
import com.example.umc9th.global.common.PageResponse;

public interface UserMissionService {
    UserMissionResponse createUserMission(UserMissionRequestCreate request);
    PageResponse<UserMissionDetailResponse> getMyInProgressMissions(Long userId, int page);
    UserMissionDetailResponse completeMission(Long userMissionId);
}
