package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResponse;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.common.PageResponse;
import com.example.umc9th.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다.")
    @GetMapping("/store/{storeId}")
    public ApiResponse<PageResponse<MissionResponse>> getStoreMissions(
        @Parameter(description = "가게 ID") @PathVariable Long storeId,
        @Parameter(description = "페이지 번호 (1부터 시작)") @ValidPage @RequestParam int page
    ) {
        PageResponse<MissionResponse> response = missionService.getStoreMissions(storeId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.GOOD_REQUEST, response);
    }
}
