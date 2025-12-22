package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.converter.MissionConverter;
import com.example.umc_spring_first.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public MissionResDTO.CreateMissionResponse createMission(MissionReqDTO.CreateMissionRequest req) {

        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Mission mission = MissionConverter.toMissionEntity(req, store);

        missionRepository.save(mission);

        return MissionConverter.toCreateMissionResponse(mission);
    }

    // 특정 가게 미션 목록
    @Transactional(readOnly = true)
    public MissionResDTO.MissionPreviewListDTO getStoreMissions(Long storeId, int page) {

        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Mission> missionPage =
                missionRepository.searchStoreMissions(storeId, pageable);

        return MissionConverter.toStoreMissionList(missionPage);
    }
}
