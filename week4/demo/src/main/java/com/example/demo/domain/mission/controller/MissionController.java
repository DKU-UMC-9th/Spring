package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.exception.code.MissionSuccessCode;
import com.example.demo.domain.mission.dto.MissionDtos;
import com.example.demo.domain.mission.service.MissionService;
import com.example.demo.global.apipayload.response.*;
import com.example.demo.global.validation.ValidPage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // =========================
    // [1] 가게 미션 등록 (사장/관리자용)
    // =========================
    @PostMapping("/markets/{marketId}/missions")
    public ResponseEntity<ApiResponse<MissionDtos.MissionResponse>> createMission(
            @PathVariable Long marketId,
            @RequestBody MissionDtos.MissionCreateRequest request
    ) {
        MissionDtos.MissionResponse body = missionService.createMission(marketId, request);
        return ApiResponse.success(MissionSuccessCode.MISSION_CREATED, body);
    }

    // =========================
    // [2] 가게 미션 목록 조회
    // =========================
    @GetMapping("/markets/{marketId}/missions")
    public ResponseEntity<ApiResponse<MissionDtos.MissionPageResponse>> getMissionsByMarket(
            @PathVariable Long marketId,
            @ValidPage
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        MissionDtos.MissionPageResponse body = missionService.getMissionsByMarket(marketId, page);
        return ApiResponse.success(MissionSuccessCode.MISSION_LIST, body);
    }

    // =========================
    // [3] 유저가 미션 수락
    // =========================
    @PostMapping("/missions/accept")
    public ResponseEntity<ApiResponse<MissionDtos.MissionUserResponse>> acceptMission(
            @RequestBody MissionDtos.AcceptRequest request
    ) {
        MissionDtos.MissionUserResponse body = missionService.acceptMission(request);
        return ApiResponse.success(MissionSuccessCode.MISSION_ACCEPTED, body);
    }

    // =========================
    // [4] 유저가 미션 완료
    // =========================
    @PostMapping("/missions/complete")
    public ResponseEntity<ApiResponse<MissionDtos.MissionUserResponse>> completeMission(
            @RequestBody MissionDtos.CompleteRequest request
    ) {
        MissionDtos.MissionUserResponse body = missionService.completeMission(request);
        return ApiResponse.success(MissionSuccessCode.MISSION_COMPLETED, body);
    }



    @GetMapping("/users/{userId}/missions")
    public ResponseEntity<ApiResponse<MissionDtos.MyMissionPageResponse>> getMyMissions(
            @PathVariable Long userId,
            @Valid @ValidPage
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        MissionDtos.MyMissionPageResponse body =
                missionService.getMyMissions(userId, page);

        return ApiResponse.success(MissionSuccessCode.MISSION_LIST, body);
    }
}
