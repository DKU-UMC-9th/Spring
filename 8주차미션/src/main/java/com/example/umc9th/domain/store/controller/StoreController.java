package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    /**
     * 1. 특정 지역에 가게 추가하기 API
     * POST /stores
     */
    @PostMapping
    public ApiResponse<StoreResDTO.CreateStoreResultDTO> createStore(
            @RequestBody @Valid StoreReqDTO.CreateStoreDTO request
    ) {
        StoreResDTO.CreateStoreResultDTO result = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

    /**
     * 2. 가게의 미션을 도전 중인 미션에 추가(미션 도전하기) API
     * POST /stores/{storeId}/missions/{missionId}/challenge
     */
    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ApiResponse<StoreResDTO.ChallengeMissionResultDTO> challengeMission(
            @PathVariable Long storeId,
            @PathVariable Long missionId,
            @RequestParam Long userId
    ) {
        StoreResDTO.ChallengeMissionResultDTO result =
                storeCommandService.challengeMission(storeId, missionId, userId);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}