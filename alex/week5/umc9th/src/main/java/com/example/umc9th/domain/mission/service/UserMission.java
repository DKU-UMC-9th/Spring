package com.example.umc9th.domain.mission.service;

import main.java.com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.domain.mission.dto.UserMissionResponse;

public interface UserMission {
    UserMissionResponse createUserMission(UserMissionRequestCreate request);
}
