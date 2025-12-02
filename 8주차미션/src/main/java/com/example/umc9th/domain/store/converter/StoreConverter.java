package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;

public class StoreConverter {

    public static Store toStore(StoreReqDTO.CreateStoreDTO dto, Location location) {
        return Store.builder()
                .name(dto.getName())
                .detailAddress(dto.getDetailAddress())
                .location(location)
                .build();
    }

    public static StoreResDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store) {
        return StoreResDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(Store store, Mission mission) {
        return StoreResDTO.ChallengeMissionResultDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .missionId(mission.getId())
                .missionName(mission.getTitle())
                .build();
    }
}