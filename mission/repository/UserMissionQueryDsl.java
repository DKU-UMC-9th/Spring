package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserMissionQueryDsl {

    Page<MissionResDTO.MissionPreviewDTO> searchMyMissions(
            Long userId,
            UserMissionStatus status,
            Pageable pageable
    );
}
