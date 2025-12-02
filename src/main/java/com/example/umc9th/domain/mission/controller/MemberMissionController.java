package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO.ChallengeResponse;
import com.example.umc9th.domain.mission.exception.code.MemberMissionSuccessCode;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.domain.mission.service.query.MemberMissionQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    // 미션 도전하기
    @PostMapping("/members/me/missions/{missionId}")
    public ApiResponse<ChallengeResponse> challenge(
            @PathVariable Long missionId) {

        ChallengeResponse response = memberMissionCommandService.challenge(missionId);
        return ApiResponse.onSuccess(MemberMissionSuccessCode.CHALLENGED, response);
    }

    // 내가 진행중인 미션 목록 API (9주차 미션)
    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "memberId 기준으로 진행중(complete=false) 상태의 미션을 page 단위(1페이지당 10개)로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공적으로 진행중인 미션을 조회했습니다."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "유효하지 않은 page 값입니다."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "회원 또는 미션을 찾을 수 없습니다."
            )
    })
    @GetMapping("/member-missions/my")
    public ApiResponse<MemberMissionResDTO.MemberMissionPreviewListDTO> getOngoingMissions(
            @RequestParam Long memberId,
            @RequestParam @ValidPage Integer page
    ){
        MemberMissionSuccessCode code = MemberMissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, memberMissionQueryService.findMyOngoingMissions(memberId, page));
    }


}
