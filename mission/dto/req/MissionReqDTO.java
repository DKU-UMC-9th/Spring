package com.example.umc_spring_first.domain.mission.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MissionReqDTO {

    // 가게에 미션 추가하기 API용 요청 DTO
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateMissionRequest {
        private Long storeId;      // 어떤 가게의 미션인지
        private Integer point;     // 미션 포인트
        private String description;// 미션 설명
    }
}
