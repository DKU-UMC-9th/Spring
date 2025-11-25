package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionDtos;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // =========================
    // [1] 가게 미션 등록
    // =========================
    @PostMapping("/markets/{marketId}/missions")
    public ResponseEntity<MissionDtos.MissionResponse> createMission(
            @PathVariable Long marketId,
            @RequestBody MissionDtos.MissionCreateRequest request
    ) {
        Mission mission = missionService.createMission(marketId, request);
        return ResponseEntity.ok(MissionDtos.MissionResponse.from(mission));
    }

    // =========================
    // [2] 가게 미션 목록 조회
    // =========================
    @GetMapping("/markets/{marketId}/missions")
    public ResponseEntity<List<MissionDtos.MissionResponse>> getMissionsByMarket(
            @PathVariable Long marketId
    ) {
        List<Mission> missions = missionService.getMissionsByMarket(marketId);
        List<MissionDtos.MissionResponse> result = missions.stream()
                .map(MissionDtos.MissionResponse::from)
                .toList();

        return ResponseEntity.ok(result);
    }

    // =========================
    // [3] 유저가 미션 수락
    // =========================
    @PostMapping("/missions/accept")
    public ResponseEntity<MissionDtos.MissionUserResponse> acceptMission(
            @RequestBody MissionDtos.AcceptRequest request
    ) {
        MissionUser mu = missionService.acceptMission(request);
        return ResponseEntity.ok(MissionDtos.MissionUserResponse.from(mu));
    }

    // =========================
    // [4] 유저가 미션 완료
    // =========================
    @PostMapping("/missions/complete")
    public ResponseEntity<MissionDtos.MissionUserResponse> completeMission(
            @RequestBody MissionDtos.CompleteRequest request
    ) {
        MissionUser mu = missionService.completeMission(request);
        return ResponseEntity.ok(MissionDtos.MissionUserResponse.from(mu));
    }
}
