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

    // 특정 가게의 미션 목록 (페이징, 1페이지부터 시작)
    @GetMapping("/store/{storeId}")
    public ApiResponse<MissionResDTO.StoreMissionPageDTO> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(name = "page") int page   // 나중에 @PositivePage 같은 커스텀 어노테이션 붙이면 됨
    ) {
        MissionResDTO.StoreMissionPageDTO result =
                missionService.getStoreMissions(storeId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.OK, result);
    }

    //내가 진행중인 미션(status = true)만 조회
    @GetMapping("/my/{memberId}/challenging")
    public ApiResponse<MissionResDTO.MyMissionPageDTO> getMyChallengingMissions(
            @PathVariable Long memberId,
            @RequestParam(name = "page") int page
    ) {
        MissionResDTO.MyMissionPageDTO result =
                missionService.getMyChallengingMissions(memberId, page);

        return ApiResponse.onSuccess(MissionSuccessCode.OK, result);
    }

}
