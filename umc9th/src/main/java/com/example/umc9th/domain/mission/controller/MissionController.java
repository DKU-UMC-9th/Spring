package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 도전하기
    @PostMapping("/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challengeMission(
            @RequestBody MissionReqDTO.ChallengeDTO dto) {

        MissionResDTO.ChallengeDTO result =
                missionService.challengeMission(dto.memberId(), dto.missionId(), dto.deadline());

        return ApiResponse.onSuccess(MissionSuccessCode.CREATE, result);
    }

    // 내 미션 목록 조회 (진행중 + 성공)
    @GetMapping("/my/{memberId}")
    public ApiResponse<List<MissionResDTO.MyMissionDTO>> getMyMissions(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                missionService.getMyMissions(memberId)
        );
    }

    // 미션 성공 처리
    @PostMapping("/success")
    public ApiResponse<MissionResDTO.SuccessDTO> successMission(
            @RequestBody MissionReqDTO.SuccessDTO dto) {

        MissionResDTO.SuccessDTO result =
                missionService.successMission(dto.memberId(), dto.userMissionId());

        return ApiResponse.onSuccess(MissionSuccessCode.SUCCESS, result);
    }
}
