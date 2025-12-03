package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionDtos;
import com.example.demo.domain.mission.exception.code.MissionSuccessCode;
import com.example.demo.domain.mission.service.MissionService;
import com.example.demo.global.apipayload.response.ApiResponse;
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

    @PostMapping("/markets/{marketId}/missions")
    public ResponseEntity<ApiResponse<MissionDtos.MissionResponse>> createMission(
            @PathVariable Long marketId,
            @Valid @RequestBody MissionDtos.MissionCreateRequest request
    ) {
        MissionDtos.MissionResponse body = missionService.createMission(marketId, request);
        return ApiResponse.success(MissionSuccessCode.MISSION_CREATED, body);
    }

    @GetMapping("/markets/{marketId}/missions")
    public ResponseEntity<ApiResponse<MissionDtos.PageResponse<MissionDtos.MissionResponse>>> getMissionsByMarket(
            @PathVariable Long marketId,
            @ValidPage
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        MissionDtos.PageResponse<MissionDtos.MissionResponse> body =
                missionService.getMissionsByMarket(marketId, page);
        return ApiResponse.success(MissionSuccessCode.MISSION_LIST, body);
    }

    @PostMapping("/missions/accept")
    public ResponseEntity<ApiResponse<MissionDtos.MissionUserResponse>> acceptMission(
            @Valid @RequestBody MissionDtos.AcceptRequest request
    ) {
        MissionDtos.MissionUserResponse body = missionService.acceptMission(request);
        return ApiResponse.success(MissionSuccessCode.MISSION_ACCEPTED, body);
    }

    @PostMapping("/missions/complete")
    public ResponseEntity<ApiResponse<MissionDtos.MissionUserResponse>> completeMission(
            @Valid @RequestBody MissionDtos.CompleteRequest request
    ) {
        MissionDtos.MissionUserResponse body = missionService.completeMission(request);
        return ApiResponse.success(MissionSuccessCode.MISSION_COMPLETED, body);
    }

    @GetMapping("/users/{userId}/missions")
    public ResponseEntity<ApiResponse<MissionDtos.PageResponse<MissionDtos.MyMissionItemResponse>>> getMyMissions(
            @PathVariable Long userId,
            @ValidPage
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        MissionDtos.PageResponse<MissionDtos.MyMissionItemResponse> body =
                missionService.getMyMissions(userId, page);
        return ApiResponse.success(MissionSuccessCode.MISSION_LIST, body);
    }
}
