package com.example.umc_spring_first.domain.mission.controller;

import com.example.umc_spring_first.domain.mission.dto.req.MissionReqDTO;
import com.example.umc_spring_first.domain.mission.dto.res.MissionResDTO;
import com.example.umc_spring_first.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_spring_first.domain.mission.service.MissionQueryService;
import com.example.umc_spring_first.domain.mission.service.MissionService;
import com.example.umc_spring_first.domain.mission.service.UserMissionService;
import com.example.umc_spring_first.global.apiPayload.ApiResponse;
import com.example.umc_spring_first.global.apiPayload.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionService missionService;
    private final UserMissionService userMissionService;
    private final MissionQueryService missionQueryService;

    // 특정 가게의 미션 목록
    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "storeId 에 해당하는 가게의 미션을 page 단위(1페이지당 10개)로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효하지 않은 page 값입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게 또는 미션을 찾을 수 없습니다.")
    })
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionQueryService.getStoreMissions(storeId, page)
        );
    }

    // 내가 진행중인 미션 목록
    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "로그인 미구현 → userId=1 로 가정하고, IN_PROGRESS 상태의 미션을 page 단위로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "유효하지 않은 page 값입니다.")
    })
    @GetMapping("/missions/my")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMyInProgressMissions(
            @RequestParam @ValidPage Integer page
    ) {
        Long userId = 1L; // 로그인 미구현

        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                missionQueryService.getMyInProgressMissions(userId, page) //실제로 조회한 미션 목록 데이터
        );
    }

    // 가게에 미션 추가하기 API
    @Operation(
            summary = "가게에 미션 추가",
            description = "요청 바디에 storeId, point, description 을 담아 새로운 미션을 생성합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없습니다.")
    })
    @PostMapping("/missions")
    public ApiResponse<MissionResDTO.CreateMissionResponse> createMission(
            @RequestBody MissionReqDTO.CreateMissionRequest req
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.CREATED,
                missionService.createMission(req)
        );
    }

    // 미션 도전하기 API
    @Operation(
            summary = "미션 도전하기",
            description = "missionId 에 해당하는 미션을 UserMission에 추가하여 도전 상태로 저장합니다. (userId=1 가정)"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 도전 등록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션을 찾을 수 없습니다.")
    })
    @PostMapping("/missions/{missionId}/challenge")
    public ApiResponse<Long> challengeMission(
            @PathVariable Long missionId
    ) {
        Long id = userMissionService.challengeMission(missionId);
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, id);
    }
}
