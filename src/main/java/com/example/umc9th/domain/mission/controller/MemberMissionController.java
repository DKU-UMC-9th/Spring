package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO.ChallengeResponse;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    // 미션 도전하기
    @PostMapping("/members/me/missions/{missionId}")
    public ApiResponse<ChallengeResponse> challenge(
            @PathVariable Long missionId) {

        ChallengeResponse response = memberMissionCommandService.challenge(missionId);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.CHALLENGED, response);
    }

}
