package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO.MissionPreviewListDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionQueryService missionQueryService;

    // 특정 가게의 미션 목록 API (9주차 미션)
    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "restaurantId 기준으로 해당 가게에 등록된 미션을 page 단위(1페이지당 10개)로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공적으로 미션을 조회했습니다."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "유효하지 않은 page 값입니다."
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "가게 또는 미션을 찾을 수 없습니다."
            )
    })
    @GetMapping("/missions")
    public ApiResponse<MissionPreviewListDTO> getMissions(
            @RequestParam Long restaurantId,
            @RequestParam @ValidPage Integer page   // 페이지 번호
    ){
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findMissions(restaurantId, page));
    }
}
