package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    @Operation(summary = "특정 가게의 미션 목록 조회", description = "Store ID로 미션 리스트를 페이징하여 조회합니다.")
    @GetMapping("/{storeId}")
    public MissionResDTO.MissionPreviewListDTO getMissions(
            @PathVariable Long storeId,
            @ValidPage @RequestParam Integer page
    ) {
        return missionQueryService.getMissionsByStore(storeId, page);
    }

    @Operation(summary = "내가 진행 중인 미션 목록 조회", description = "User ID로 진행 중인 미션 리스트를 페이징하여 조회합니다.")
    @GetMapping("/user/{userId}")
    public MissionResDTO.MissionPreviewListDTO getUserMissions(
            @PathVariable Long userId,
            @ValidPage @RequestParam(defaultValue = "1") Integer page
    ) {
        return missionQueryService.getMissionsByUser(userId, page);
    }
}
