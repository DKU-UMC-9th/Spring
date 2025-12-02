package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Users;
import com.example.demo.domain.member.repository.UsersRepository;
import com.example.demo.domain.mission.dto.MissionDtos;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionStatus;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.exception.MissionException;
import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.repository.MissionUserRepository;
import com.example.demo.domain.restruant.entity.FoodMarket;
import com.example.demo.domain.restruant.entity.FoodMarketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    // [2] 가게의 미션 리스트 조회 (페이징, 한 페이지 10개)
    //  - 프론트: page >= 1
    //  - JPA: 0-based index → page - 1
    // =========================
    public MissionDtos.MissionPageResponse getMissionsByMarket(Long marketId, int page) {
        FoodMarket market = foodMarketRepository.findById(marketId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MARKET_NOT_FOUND));

        int pageIndex = page - 1; // 1-based → 0-based 변환
        PageRequest pageable = PageRequest.of(pageIndex, 10);

        Page<Mission> missions = missionRepository.findByMarket(market, pageable);

        return MissionDtos.MissionPageResponse.builder()
                .content(
                        missions.getContent()
                                .stream()
                                .map(MissionDtos.MissionResponse::from)
                                .toList()
                )
                .page(page)                       // 1-based 그대로 반환
                .size(missions.getSize())
                .totalElements(missions.getTotalElements())
                .totalPages(missions.getTotalPages())
                .last(missions.isLast())
                .build();
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

        // 필요하다면 완료 조건 체크 로직 추가 가능
        // ...

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
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_ACCEPTED));

        return MissionDtos.MissionUserResponse.from(mu);
    }
    public MissionDtos.MyMissionPageResponse getMyMissions(Long userId, int page) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        int pageIndex = page - 1;              // 1-based → 0-based
        PageRequest pageable = PageRequest.of(pageIndex, 10);

        Page<MissionUser> muPage =
                missionUserRepository.findByUser_IdOrderByUpdatedAtDesc(userId, pageable);

        return MissionDtos.MyMissionPageResponse.builder()
                .content(
                        muPage.getContent().stream()
                                .map(MissionDtos.MyMissionItemResponse::from)
                                .toList()
                )
                .page(page)
                .size(muPage.getSize())
                .totalElements(muPage.getTotalElements())
                .totalPages(muPage.getTotalPages())
                .last(muPage.isLast())
                .build();
    }

}
