package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.domain.mission.dto.UserMissionRequestCreate;

public interface UserMissionService {
    UserMissionResponse createUserMission(UserMissionRequestCreate request);
}
