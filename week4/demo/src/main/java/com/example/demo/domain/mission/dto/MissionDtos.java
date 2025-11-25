package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.entity.MissionStatus;

public class MissionDtos {

    public record MissionCreateRequest(
            String content,
            Integer missionPoint
    ) {}


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
                    mission.getMarket().getName(),
                    mission.getContent(),
                    mission.getMissionPoint()
            );
        }
    }

    public record AcceptRequest(
            Long missionId,
            Long userId
    ) {}


    public record CompleteRequest(
            Long missionId,
            Long userId,
            String content    // 인증 내용
    ) {}


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
