package com.example.week4.service;

import com.example.week4.domain.Mission;
import com.example.week4.domain.User;
import com.example.week4.domain.UserMission;
import com.example.week4.repository.MissionRepository;
import com.example.week4.repository.UserMissionRepository;
import com.example.week4.repository.UserRepository;
import com.example.week4.web.dto.MissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public UserMission challengeMission(MissionRequestDTO.ChallengeMissionDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .is_complete(false)
                .build();

        return userMissionRepository.save(userMission);
    }

    @Override
    @Transactional
    public void completeMission(Long userId, Long missionId) {
        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found or not challenged"));
        userMission.complete();
    }
}
