package com.example.week4.controller;

import com.example.week4.apiPayload.ApiResponse;
import com.example.week4.apiPayload.code.GeneralSuccessCode;
import com.example.week4.domain.UserMission;
import com.example.week4.service.MissionCommandService;
import com.example.week4.web.dto.MissionRequestDTO;
import com.example.week4.web.dto.MissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDto> challengeMission(
            @RequestBody MissionRequestDTO.ChallengeMissionDto request
    ) {
        UserMission userMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_CHALLENGED, MissionResponseDTO.ChallengeMissionResultDto.from(userMission));
    }
}
