package com.example.week4.service;

import com.example.week4.domain.Mission;
import com.example.week4.domain.Store;
import com.example.week4.domain.User;
import com.example.week4.domain.UserMission;
import com.example.week4.repository.MissionRepository;
import com.example.week4.repository.StoreRepository;
import com.example.week4.repository.UserMissionRepository;
import com.example.week4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        return missionRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
    }

    @Override
    public Page<UserMission> getMyOngoingMissions(Long userId, Integer page) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMissionRepository.findAllByUserAndIsComplete(user, false, PageRequest.of(page - 1, 10));
    }
}
