package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.entity.mapping.UserMission;
import com.example.umc9th.domain.user.enums.UserMissionStatus;
import com.example.umc9th.domain.user.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;

    public MissionResDTO.MissionPreviewListDTO getMissionsByStore(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        PageRequest pageable = PageRequest.of(page - 1, 10);

        Page<Mission> missionPage = missionRepository.findByStore(store, pageable);

        return MissionConverter.toMissionPreviewListDTOFromMission(missionPage);
    }

    public MissionResDTO.MissionPreviewListDTO getMissionsByUser(Long userId, Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, 10);

        Page<UserMission> missionPage = userMissionRepository.findByUserIdAndStatus(
                userId,
                UserMissionStatus.CHALLENGING,
                pageable
        );

        return MissionConverter.toMissionPreviewListDTOFromUserMission(missionPage);
    }
}
