package com.example.umc9th.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberMissionResDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChallengeResponse {
        private Long memberMissionId;
        private Long missionId;
        private boolean complete;
    }
}
