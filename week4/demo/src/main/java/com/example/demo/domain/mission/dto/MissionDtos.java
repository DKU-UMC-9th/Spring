package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.entity.MissionStatus;
import lombok.Builder;

import java.util.List;

public class MissionDtos {

    @Builder
    public record MissionCreateRequest(
            String content,
            Integer missionPoint
    ) {}

    @Builder
    public record MissionResponse(
            Long id,
            Long marketId,
            String marketName,
            String content,
            Integer missionPoint
    ) {
        public static MissionResponse from(Mission mission) {
            return MissionResponse.builder()
                    .id(mission.getId())
                    .marketId(mission.getMarket().getId())
                    .marketName(mission.getMarket().getName())
                    .content(mission.getContent())
                    .missionPoint(mission.getMissionPoint())
                    .build();
        }
    }

    // 페이징 응답 DTO (10개씩)
    @Builder
    public record MissionPageResponse(
            List<MissionResponse> content,
            int page,           // 현재 페이지 (1-based)
            int size,           // 페이지 크기
            long totalElements, // 전체 개수
            int totalPages,     // 전체 페이지 수
            boolean last        // 마지막 페이지 여부
    ) {}

    @Builder
    public record AcceptRequest(
            Long missionId,
            Long userId
    ) {}

    @Builder
    public record CompleteRequest(
            Long missionId,
            Long userId,
            String content    // 인증 내용
    ) {}

    @Builder
    public record MissionUserResponse(
            Long missionUserId,
            Long missionId,
            Long userId,
            MissionStatus status,
            String content
    ) {
        public static MissionUserResponse from(MissionUser mu) {
            return MissionUserResponse.builder()
                    .missionUserId(mu.getId())
                    .missionId(mu.getMission().getId())
                    .userId(mu.getUser().getId())
                    .status(mu.getMissionStatus())
                    .content(mu.getContent())
                    .build();
        }
    }
    @Builder
    public record MyMissionItemResponse(
            Long missionUserId,
            Long missionId,
            Long marketId,
            String marketName,
            String missionContent,
            Integer missionPoint,
            MissionStatus status
    ) {
        public static MyMissionItemResponse from(MissionUser mu) {
            Mission mission = mu.getMission();
            return MyMissionItemResponse.builder()
                    .missionUserId(mu.getId())
                    .missionId(mission.getId())
                    .marketId(mission.getMarket().getId())
                    .marketName(mission.getMarket().getName())
                    .missionContent(mission.getContent())
                    .missionPoint(mission.getMissionPoint())
                    .status(mu.getMissionStatus())
                    .build();
        }
    }
    @Builder
    public record MyMissionPageResponse(
            List<MyMissionItemResponse> content,
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean last
    ) {}

}
