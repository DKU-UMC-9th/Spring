package com.example.umc9th.domain.mission.dto.res;

import java.time.LocalDateTime;
import java.util.List;
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

    @Builder
    public record MemberMissionPreviewListDTO(
            List<MemberMissionPreviewDTO> memberMissionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MemberMissionPreviewDTO(
            Long memberMissionId,
            Long missionId,
            String restaurantName,
            String content,
            int point,
            LocalDateTime expiredAt,
            boolean complete
    ){}
}
