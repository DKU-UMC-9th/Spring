package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.mission.repository.UserMissionRepository;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public Long challengeMission(Long missionId) {

        Long userId = 1L; // 로그인 없음 → 하드코딩

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션 없음"));

        UserMission um = UserMission.builder()
                .mission(mission)
                .user(user)
                .status(UserMissionStatus.IN_PROGRESS)
                .build();

        userMissionRepository.save(um);

        return um.getId();
    }
}
