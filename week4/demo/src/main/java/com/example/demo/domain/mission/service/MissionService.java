package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Users;
import com.example.demo.domain.member.repository.UsersRepository;
import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import com.example.demo.domain.mission.dto.MissionDtos;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionStatus;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.exception.MissionException;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.repository.MissionUserRepository;
import com.example.demo.domain.restruant.entity.FoodMarket;
import com.example.demo.domain.restruant.entity.FoodMarketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionUserRepository missionUserRepository;
    private final UsersRepository usersRepository;
    private final FoodMarketRepository foodMarketRepository;

    // =========================
    // [1] 가게 미션 등록 (사장/관리자용)
    // =========================
    public MissionDtos.MissionResponse createMission(
            Long marketId,
            MissionDtos.MissionCreateRequest request
    ) {
        FoodMarket market = foodMarketRepository.findById(marketId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MARKET_NOT_FOUND));

        Mission mission = new Mission();
        mission.setMarket(market);
        mission.setContent(request.content());
        mission.setMissionPoint(request.missionPoint());

        Mission saved = missionRepository.save(mission);
        return MissionDtos.MissionResponse.from(saved);
    }

    // =========================
    // [2] 가게의 미션 리스트 조회
    // =========================
    public List<MissionDtos.MissionResponse> getMissionsByMarket(Long marketId) {
        FoodMarket market = foodMarketRepository.findById(marketId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MARKET_NOT_FOUND));

        // MissionRepository에 아래 메서드가 정의되어 있다고 가정:
        // List<Mission> findByMarket(FoodMarket market);
        List<Mission> missions = missionRepository.findByMarket(market);

        return missions.stream()
                .map(MissionDtos.MissionResponse::from)
                .toList();
    }

    // =========================
    // [3] 유저가 미션 수락
    // =========================
    public MissionDtos.MissionUserResponse acceptMission(MissionDtos.AcceptRequest request) {
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Users user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        // 이미 수락/완료한 미션인지 체크
        if (missionUserRepository.existsByMissionAndUser(mission, user)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_ACCEPTED);
        }

        // 필요하다면 조건 체크:
        // if (!canAccept(mission, user)) {
        //     throw new BusinessException(MissionErrorCode.MISSION_CONDITION_NOT_MET);
        // }

        MissionUser mu = new MissionUser();
        mu.setMission(mission);
        mu.setUser(user);
        mu.setMissionStatus(MissionStatus.ACCEPTED);

        MissionUser saved = missionUserRepository.save(mu);
        return MissionDtos.MissionUserResponse.from(saved);
    }

    // =========================
    // [4] 유저가 미션 완료
    // =========================
    public MissionDtos.MissionUserResponse completeMission(MissionDtos.CompleteRequest request) {
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Users user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        MissionUser mu = missionUserRepository.findByMissionAndUser(mission, user)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_ACCEPTED));

        if (mu.getMissionStatus() == MissionStatus.COMPLETED) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        // 완료 조건 체크 필요시:
        // if (!canComplete(mission, user)) {
        //     throw new BusinessException(MissionErrorCode.MISSION_CONDITION_NOT_MET);
        // }

        mu.setMissionStatus(MissionStatus.COMPLETED);
        mu.setContent(request.content());

        MissionUser updated = missionUserRepository.save(mu);
        return MissionDtos.MissionUserResponse.from(updated);
    }

    // =========================
    // [5] (선택) 유저의 특정 미션 상태 조회
    // =========================
    public MissionDtos.MissionUserResponse getMissionUser(Long missionId, Long userId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        MissionUser mu = missionUserRepository.findByMissionAndUser(mission, user)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_ACCEPTED)
                );

        return MissionDtos.MissionUserResponse.from(mu);
    }
}
