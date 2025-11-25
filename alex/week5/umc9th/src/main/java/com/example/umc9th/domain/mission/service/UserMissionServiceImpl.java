package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;

import jakarta.transaction.Transactional;


public class UserMissionServiceImpl implements UserMissionService{
    
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserMissionResponse createUserMission(UserMissionRequestCreate request){
        
        User user = userRepository.findById(request.userId())
                                     .orElseThrow(() -> new GeneralException(GeneralErrorCode.INVALID_DATA));

        Mission mission = missionRepository.findById(request.missionId())
                                     .orElseThrow(() -> new GeneralException(GeneralErrorCode.INVALID_DATA));


        UserMission userMission = UserMission.builder()
                                             .user(user)
                                             .mission(mission)
                                             .isComplete(request.isComplete())
                                             .completeAt(request.compeletAt());
        
        UserMission saved = userMissionRepository.save(userMission);

        return toResponse(saved);
    }

    private UserMissionResponse toResponse(UserMission userMission){
        return UserMissionResponse(
            userMission.getId(),
            userMission.getMission().getId(),
            userMission.getIsComplete(),
            userMission.getCompleteAt()
        );
    }
   
}
