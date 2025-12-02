package com.example.week4.service;

import com.example.week4.domain.Mission;
import com.example.week4.domain.UserMission;
import org.springframework.data.domain.Page;

public interface MissionQueryService {
    Page<Mission> getStoreMissions(Long storeId, Integer page);
    Page<UserMission> getMyOngoingMissions(Long userId, Integer page);
}
