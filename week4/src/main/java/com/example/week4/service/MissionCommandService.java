package com.example.week4.service;

import com.example.week4.domain.UserMission;
import com.example.week4.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    UserMission challengeMission(MissionRequestDTO.ChallengeMissionDto request);
}
