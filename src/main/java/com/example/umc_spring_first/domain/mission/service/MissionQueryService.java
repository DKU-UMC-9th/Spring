package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.converter.MissionConverter;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    // 2번: 특정 가게의 미션 목록
    public MissionResDTO.MissionPreviewListDTO getStoreMissions(Long storeId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        var result = missionRepository.searchStoreMissions(storeId, pageable);

        return MissionConverter.toMissionPreviewListDTO(result);
    }

    // 3번: 내가 진행중인 미션 목록
    public MissionResDTO.MissionPreviewListDTO getMyInProgressMissions(Long userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);

        var result = userMissionRepository.searchMyMissions(
                userId,
                UserMissionStatus.IN_PROGRESS,
                pageable
        );

        return MissionConverter.toMissionPreviewListDTO(result);
    }
}
