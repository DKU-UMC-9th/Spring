package com.example.umc_spring_first.domain.mission.repository;

import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryDsl {

    // 2번: 특정 가게의 미션 목록
    Page<MissionResDTO.MissionPreviewDTO> searchStoreMissions(
            Long storeId,
            Pageable pageable
    );

    // 3번: 내가 진행중인 미션 목록
    Page<MissionResDTO.MissionPreviewDTO> searchMyInProgressMissions(
            Long userId,
            Pageable pageable
    );
}
