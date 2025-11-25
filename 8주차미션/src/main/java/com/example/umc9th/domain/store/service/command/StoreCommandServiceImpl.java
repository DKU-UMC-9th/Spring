package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.LocationRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.mapping.UserMission;
import com.example.umc9th.domain.user.enums.UserMissionStatus;
import com.example.umc9th.domain.user.exception.UserException;
import com.example.umc9th.domain.user.repository.UserMissionRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.domain.user.exception.code.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    public StoreResDTO.CreateStoreResultDTO createStore(StoreReqDTO.CreateStoreDTO dto) {
        // 지역 조회
        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCATION_NOT_FOUND));

        // Store 엔티티 생성
        Store store = StoreConverter.toStore(dto, location);

        // DB 저장
        Store savedStore = storeRepository.save(store);

        // 응답 DTO 반환
        return StoreConverter.toCreateStoreResultDTO(savedStore);
    }

    @Override
    public StoreResDTO.ChallengeMissionResultDTO challengeMission(Long storeId, Long missionId, Long userId) {
        // 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 조회
        Mission mission = store.getMissionList().stream()
                .filter(m -> m.getId().equals(missionId))
                .findFirst()
                .orElseThrow(() -> new StoreException(StoreErrorCode.MISSION_NOT_FOUND_IN_STORE));

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        Optional<UserMission> existing = userMissionRepository.findByUserIdAndMissionId(userId, missionId);
        if(existing.isPresent()) {
            throw new UserException(UserErrorCode.ALREADY_CHALLENGING);
        }

        // UserMission 엔티티 생성
        UserMission userMission = UserMission.builder()
                .mission(mission)
                .user(user)
                .status(UserMissionStatus.CHALLENGING) // 도전 중 상태로 설정
                .build();

        // DB 저장
        userMissionRepository.save(userMission);

        // 응답 DTO 반환
        return StoreConverter.toChallengeMissionResultDTO(store, mission);
    }
}