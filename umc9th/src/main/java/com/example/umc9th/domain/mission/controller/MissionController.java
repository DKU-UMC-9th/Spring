package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/challenge")
    public ApiResponse<MissionResDTO.ChallengeDTO> challengeMission(
            @RequestBody MissionReqDTO.ChallengeDTO dto) {

        MissionResDTO.ChallengeDTO result =
                missionService.challengeMission(dto.memberId(), dto.missionId(), dto.deadline());

        return ApiResponse.onSuccess(MissionSuccessCode.CREATE, result);
    }
}