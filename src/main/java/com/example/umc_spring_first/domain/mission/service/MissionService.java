package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.dto.MissionCreateRequest;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public Long addMission(Long storeId, MissionCreateRequest req) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        Mission mission = Mission.builder()
                .description(req.getDescription())
                .point(req.getPoint())
                .store(store)
                .build();

        missionRepository.save(mission);
        return mission.getId();
    }
}
