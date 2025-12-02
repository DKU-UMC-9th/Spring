package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.dto.MissionCreateRequest;
import com.example.umc_spring_first.domain.mission.dto.MissionRowDto;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // ★ 추가: 특정 가게의 미션 목록 조회
    @Transactional(readOnly = true)
    public Page<MissionRowDto> getStoreMissions(Long storeId, int page) {
        Pageable pageable = PageRequest.of(page, 10); // 한 페이지당 10개
        return missionRepository.findByStoreId(storeId, pageable);
    }
}
