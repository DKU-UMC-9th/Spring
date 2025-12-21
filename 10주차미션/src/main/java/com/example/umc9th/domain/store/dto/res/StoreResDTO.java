package com.example.umc9th.domain.store.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreResDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreResultDTO {
        private Long storeId;
        private String name;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChallengeMissionResultDTO {
        private Long storeId;
        private String storeName;
        private Long missionId;
        private String missionName;
    }
}