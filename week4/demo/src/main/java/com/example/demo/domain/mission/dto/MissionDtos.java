package com.example.demo.domain.mission.dto;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.MissionUser;
import com.example.demo.domain.mission.entity.MissionStatus;
import lombok.Builder;

import java.util.List;

public class MissionDtos {

    // 공통 페이지 응답 DTO (제네릭)
    @Builder
    public record PageResponse<T>(
            List<T> content,
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean last
    ) {}

    @Builder
    public record MissionCreateRequest(
            String content,
            Integer missionPoint
    ) {}

    // 미션 기본 정보 (모든 곳에서 재사용)
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

    // 개별 유저-미션 정보 (상세 조회/accept/complete 응답 등에 사용)
    @Builder
    public record MissionUserResponse(
            Long missionUserId,
            MissionResponse mission,   // 미션 정보 재사용
            Long userId,
            MissionStatus status,
            String content
    ) {
        public static MissionUserResponse from(MissionUser mu) {
            return MissionUserResponse.builder()
                    .missionUserId(mu.getId())
                    .mission(MissionResponse.from(mu.getMission()))
                    .userId(mu.getUser().getId())
                    .status(mu.getMissionStatus())
                    .content(mu.getContent())
                    .build();
        }
    }

    // "내 미션 목록" 한 줄 (리스트용)
    @Builder
    public record MyMissionItemResponse(
            Long missionUserId,
            MissionResponse mission,   // 동일하게 재사용
            MissionStatus status,
            String content
    ) {
        public static MyMissionItemResponse from(MissionUser mu) {
            return MyMissionItemResponse.builder()
                    .missionUserId(mu.getId())
                    .mission(MissionResponse.from(mu.getMission()))
                    .status(mu.getMissionStatus())
                    .content(mu.getContent())
                    .build();
        }
    }
}
