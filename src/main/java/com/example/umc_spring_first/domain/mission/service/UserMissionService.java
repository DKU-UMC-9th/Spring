package com.example.umc_spring_first.domain.mission.service;

import com.example.umc_spring_first.domain.mission.converter.MissionConverter;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.entity.Mission;
import com.example.umc_spring_first.domain.mission.entity.UserMission;
import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import com.example.umc_spring_first.domain.mission.repository.MissionRepository;
import com.example.umc_spring_first.domain.mission.repository.UserMissionRepository;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    // 미션 도전하기
    public Long challengeMission(Long missionId) {

        Long userId = 1L;   // 로그인 미구현 → 하드코딩

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션 없음"));

        // Converter 사용해서 UserMission 엔티티 생성
        UserMission um = MissionConverter.toUserMissionEntity(user, mission);

        userMissionRepository.save(um);

        // 컨트롤러에서 Long id를 반환받으므로 Long 리턴
        return um.getId();
    }

    //내가 진행중인 미션 목록 조회
    @Transactional(readOnly = true)
    public MissionResDTO.MissionPreviewListDTO getMyMissions(int page) {

        Long userId = 1L;
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<UserMission> entityPage =
                userMissionRepository.searchMyMissions(
                        userId,
                        UserMissionStatus.IN_PROGRESS,
                        pageable
                );

        return MissionConverter.toMissionPreviewList(entityPage);
    }
}
