package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserMissionQueryDsl {
    Page<UserMission> searchMyMissions( //구현체인 impl이 뭘 구현해야하는지 명시해줌.
            Long userId,
            UserMissionStatus status,
            Pageable pageable
    );
}
