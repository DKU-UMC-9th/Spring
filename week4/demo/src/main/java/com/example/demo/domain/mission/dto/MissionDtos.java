package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.entity.MissionStatus;

public class MissionDtos {

    // ========== [가게 미션 등록 / 조회] ==========

    // 가게 미션 생성 요청용 DTO
    public record MissionCreateRequest(
            String content,
            Integer missionPoint
    ) {}

    // 가게 미션 응답 DTO
    public record MissionResponse(
            Long id,
            Long marketId,
            String marketName,
            String content,
            Integer missionPoint
    ) {
        public static MissionResponse from(Mission mission) {
            return new MissionResponse(
                    mission.getId(),
                    mission.getMarket().getId(),
                    mission.getMarket().getName(),   // FoodMarket에 getName() 있다고 가정
                    mission.getContent(),
                    mission.getMissionPoint()
            );
        }
    }

    // ========== [유저 미션 수락 / 완료] ==========

    // 미션 수락 요청
    public record AcceptRequest(
            Long missionId,
            Long userId
    ) {}

    // 미션 완료 요청
    public record CompleteRequest(
            Long missionId,
            Long userId,
            String content    // 인증 내용
    ) {}

    // 유저 미션 상태 응답
    public record MissionUserResponse(
            Long missionUserId,
            Long missionId,
            Long userId,
            MissionStatus status,
            String content
    ) {
        public static MissionUserResponse from(MissionUser mu) {
            return new MissionUserResponse(
                    mu.getId(),
                    mu.getMission().getId(),
                    mu.getUser().getId(),
                    mu.getMissionStatus(),
                    mu.getContent()
            );
        }
    }
}
