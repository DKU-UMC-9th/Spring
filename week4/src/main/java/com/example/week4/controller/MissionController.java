package com.example.week4.controller;

import com.example.week4.apiPayload.ApiResponse;
import com.example.week4.apiPayload.code.GeneralSuccessCode;
import com.example.week4.converter.MissionConverter;
import com.example.week4.domain.Mission;
import com.example.week4.domain.UserMission;
import com.example.week4.service.MissionCommandService;
import com.example.week4.service.MissionQueryService;
import com.example.week4.validation.annotation.CheckPage;
import com.example.week4.web.dto.MissionRequestDTO;
import com.example.week4.web.dto.MissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDto> challengeMission(
            @RequestBody MissionRequestDTO.ChallengeMissionDto request
    ) {
        UserMission userMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_CHALLENGED, MissionResponseDTO.ChallengeMissionResultDto.from(userMission));
    }

    @GetMapping("/stores/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들을 조회하는 API이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionListDto> getMissionsByStore(
            @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        Page<Mission> missionPage = missionQueryService.getStoreMissions(storeId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_LIST_FETCHED, MissionConverter.toMissionListDto(missionPage));
    }

    @GetMapping("/users/{userId}/ongoing")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션들을 조회하는 API이며, 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionListDto> getMyOngoingMissions(
            @PathVariable(name = "userId") Long userId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        Page<UserMission> userMissionPage = missionQueryService.getMyOngoingMissions(userId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_LIST_FETCHED, MissionConverter.toMissionListDtoFromUserMission(userMissionPage));
    }

    @PatchMapping("/users/{userId}/missions/{missionId}/complete")
    @Operation(summary = "진행중인 미션 진행 완료로 바꾸기 API", description = "진행중인 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<Object> completeMission(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "missionId") Long missionId
    ) {
        missionCommandService.completeMission(userId, missionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.MISSION_COMPLETED, null);
    }
}