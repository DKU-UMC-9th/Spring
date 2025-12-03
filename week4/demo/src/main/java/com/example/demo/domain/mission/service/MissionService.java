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

    public MissionDtos.PageResponse<MissionDtos.MissionResponse> getMissionsByMarket(Long marketId, int page) {
        FoodMarket market = foodMarketRepository.findById(marketId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MARKET_NOT_FOUND));

        int pageIndex = page - 1;
        PageRequest pageable = PageRequest.of(pageIndex, 10);

        Page<Mission> missions = missionRepository.findByMarket(market, pageable);

        return MissionDtos.PageResponse.<MissionDtos.MissionResponse>builder()
                .content(
                        missions.getContent()
                                .stream()
                                .map(MissionDtos.MissionResponse::from)
                                .toList()
                )
                .page(page)
                .size(missions.getSize())
                .totalElements(missions.getTotalElements())
                .totalPages(missions.getTotalPages())
                .last(missions.isLast())
                .build();
    }

    public MissionDtos.MissionUserResponse acceptMission(MissionDtos.AcceptRequest request) {
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Users user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

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

        mu.setMissionStatus(MissionStatus.COMPLETED);
        mu.setContent(request.content());

        MissionUser updated = missionUserRepository.save(mu);
        return MissionDtos.MissionUserResponse.from(updated);
    }


    public MissionDtos.PageResponse<MissionDtos.MyMissionItemResponse> getMyMissions(Long userId, int page) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        int pageIndex = page - 1;
        PageRequest pageable = PageRequest.of(pageIndex, 10);

        Page<MissionUser> muPage =
                missionUserRepository.findByUser_IdOrderByUpdatedAtDesc(user.getId(), pageable);

        return MissionDtos.PageResponse.<MissionDtos.MyMissionItemResponse>builder()
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
