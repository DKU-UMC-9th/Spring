package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.UserMissionDetailResponse;
import com.example.umc9th.global.common.PageResponse;
import com.example.umc9th.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

import com.example.umc9th.domain.mission.service.UserMissionService;
import com.example.umc9th.domain.mission.dto.UserMissionResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.domain.mission.dto.UserMissionRequestCreate;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/mission/user")
@RequiredArgsConstructor
@Tag(name = "UserMission", description = "유저 미션 관련 API")
public class UserMissionController {

    private final UserMissionService userMissionService;

    @Operation(summary = "유저 미션 생성", description = "사용자가 미션에 도전합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 도전 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/create")
    public ApiResponse<UserMissionResponse> Create(
        @Parameter(description = "유저 미션 생성 요청 데이터") @Valid @RequestBody UserMissionRequestCreate request
    ) {
        UserMissionResponse resp = userMissionService.createUserMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATE, resp);
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "사용자가 진행중인 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호")
    })
    @GetMapping("/in-progress")
    public ApiResponse<PageResponse<UserMissionDetailResponse>> getMyInProgressMissions(
        @Parameter(description = "사용자 ID") @RequestParam Long userId,
        @Parameter(description = "페이지 번호 (1부터 시작)") @ValidPage @RequestParam int page
    ) {
        PageResponse<UserMissionDetailResponse> response = userMissionService.getMyInProgressMissions(userId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.GOOD_REQUEST, response);
    }

    @Operation(summary = "미션 완료 처리", description = "진행중인 미션을 완료 상태로 변경하고 변경된 미션을 조회합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 완료 처리 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션을 찾을 수 없음"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 완료된 미션")
    })
    @PatchMapping("/{userMissionId}/complete")
    public ApiResponse<UserMissionDetailResponse> completeMission(
        @Parameter(description = "유저 미션 ID") @PathVariable Long userMissionId
    ) {
        UserMissionDetailResponse response = userMissionService.completeMission(userMissionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.GOOD_REQUEST, response);
    }
}