package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.global.common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MissionConverter missionConverter;

    @Override
    public PageResponse<MissionResponse> getStoreMissions(Long storeId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Mission> missionPage = missionRepository.findByStoreIdAndDbStatusTrue(storeId, pageable);

        Page<MissionResponse> responsePage = missionPage.map(missionConverter::toResponse);

        return PageResponse.of(responsePage);
    }
}
