package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.converter.MissionConverter;
import com.example.umc_spring_first.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    // 3번: 가게에 미션 추가하기 API용
    public MissionResDTO.CreateMissionResponse createMission(MissionReqDTO.CreateMissionRequest req) {

        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        Mission mission = Mission.builder()
                .store(store)
                .point(req.getPoint())
                .description(req.getDescription())
                .build();

        missionRepository.save(mission);

        return MissionConverter.toCreateMissionResponse(mission);
    }
}
