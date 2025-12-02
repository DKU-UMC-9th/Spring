// src/main/java/com/example/umc_spring_first/domain/mission/service/MissionQueryService.java
package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.converter.MissionConverter;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    // 2번: 특정 가게의 미션 목록
    public MissionResDTO.StoreMissionPreviewListDTO getStoreMissions(Long storeId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);   // 프론트는 1부터 주니까 -1
        Page<MissionResDTO.StoreMissionPreviewDTO> result =
                missionRepository.findStoreMissions(storeId, pageable);

        return MissionConverter.toStoreMissionListDTO(result);
    }

    // 3번: 내가 진행중인 미션 목록
    public MissionResDTO.MyMissionPreviewListDTO getMyInProgressMissions(Long userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<MissionResDTO.MyMissionPreviewDTO> result =
                userMissionRepository.findMyMissions(userId, UserMissionStatus.IN_PROGRESS, pageable);

        return MissionConverter.toMyMissionListDTO(result);
    }
}
