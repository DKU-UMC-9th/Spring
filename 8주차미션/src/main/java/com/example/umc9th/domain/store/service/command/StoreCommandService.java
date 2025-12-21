package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.CreateStoreResultDTO createStore(StoreReqDTO.CreateStoreDTO dto);
    StoreResDTO.ChallengeMissionResultDTO challengeMission(Long storeId, Long missionId, Long userId);
}

