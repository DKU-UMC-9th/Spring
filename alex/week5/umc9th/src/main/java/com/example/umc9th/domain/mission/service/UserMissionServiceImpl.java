package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.UserMissionConverter;
import com.example.umc9th.domain.mission.dto.UserMissionDetailResponse;
import com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.domain.mission.dto.UserMissionRequestCreate;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.common.PageResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserMissionServiceImpl implements UserMissionService{

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionConverter userMissionConverter;

    @Override
    @Transactional
    public UserMissionResponse createUserMission(UserMissionRequestCreate request){

        if (userMissionRepository.existsByUserIdAndMissionId(request.userId(), request.missionId())) {
            throw new GeneralException(GeneralErrorCode.DUPLICATE_MISSION);
        }

        User user = userRepository.findById(request.userId())
                                     .orElseThrow(() -> new GeneralException(GeneralErrorCode.INVALID_DATA));

        Mission mission = missionRepository.findById(request.missionId())
                                     .orElseThrow(() -> new GeneralException(GeneralErrorCode.INVALID_DATA));


        UserMission userMission = UserMission.builder()
                                             .user(user)
                                             .mission(mission)
                                             .isComplete(request.isComplete())
                                             .completeAt(request.completeAt())
                                             .build();

        UserMission saved = userMissionRepository.save(userMission);

        return toResponse(saved);
    }

    @Override
    public PageResponse<UserMissionDetailResponse> getMyInProgressMissions(Long userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "mission.deadline"));

        Page<UserMission> missionPage = userMissionRepository.findByUserIdAndIsCompleteFalse(userId, pageable);

        Page<UserMissionDetailResponse> responsePage = missionPage.map(userMissionConverter::toDetailResponse);

        return PageResponse.of(responsePage);
    }

    @Override
    @Transactional
    public UserMissionDetailResponse completeMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        if (userMission.getIsComplete()) {
            throw new GeneralException(GeneralErrorCode.INVALID_DATA);
        }

        userMission.completeNow();

        UserMission saved = userMissionRepository.save(userMission);

        return userMissionConverter.toDetailResponse(saved);
    }

    private UserMissionResponse toResponse(UserMission userMission){
        return new UserMissionResponse(
            userMission.getId(),
            userMission.getMission().getId(),
            userMission.getIsComplete(),
            userMission.getCompleteAt()
        );
    }

}
