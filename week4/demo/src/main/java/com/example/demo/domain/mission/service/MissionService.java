package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Users;
import com.example.demo.domain.member.repository.UsersRepository;
import com.example.demo.domain.mission.dto.MissionDtos;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionStatus;
import com.example.demo.domain.mission.entity.MissionUser;
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
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionUserRepository missionUserRepository;
    private final UsersRepository usersRepository;
    private final FoodMarketRepository foodMarketRepository;

    // =========================
    // [1] 가게 미션 등록
    // =========================
    @Transactional
    public Mission createMission(Long marketId, MissionDtos.MissionCreateRequest request) {
        FoodMarket market = foodMarketRepository.findById(marketId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Mission mission = new Mission();
        mission.setMarket(market);
        mission.setContent(request.content());
        mission.setMissionPoint(request.missionPoint());

        return missionRepository.save(mission);
    }

    // =========================
    // [2] 가게의 미션 리스트 조회
    // =========================
    @Transactional
    public List<Mission> getMissionsByMarket(Long marketId) {
        FoodMarket market = foodMarketRepository.findById(marketId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        // 단순 예시: market 기준으로 필터
        // (MissionRepository에 findByMarket(...) 를 추가하는 게 더 깔끔)
        return missionRepository.findAll().stream()
                .filter(m -> m.getMarket().equals(market))
                .toList();
    }

    // =========================
    // [3] 미션 수락
    // =========================
    @Transactional
    public MissionUser acceptMission(MissionDtos.AcceptRequest request) {
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        Users user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        if (missionUserRepository.existsByMissionAndUser(mission, user)) {
            throw new IllegalStateException("이미 이 미션을 수락했거나 완료했습니다.");
        }

        MissionUser mu = new MissionUser();
        mu.setMission(mission);
        mu.setUser(user);
        mu.setMissionStatus(MissionStatus.ACCEPTED);

        return missionUserRepository.save(mu);
    }

    // =========================
    // [4] 미션 완료
    // =========================
    @Transactional
    public MissionUser completeMission(MissionDtos.CompleteRequest request) {
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));

        Users user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        MissionUser mu = missionUserRepository.findByMissionAndUser(mission, user)
                .orElseThrow(() -> new IllegalStateException("아직 이 미션을 수락하지 않았습니다."));

        if (mu.getMissionStatus() == MissionStatus.COMPLETED) {
            throw new IllegalStateException("이미 완료된 미션입니다.");
        }

        mu.setMissionStatus(MissionStatus.COMPLETED);;
        mu.setContent(request.content());

        return mu;
    }
}
