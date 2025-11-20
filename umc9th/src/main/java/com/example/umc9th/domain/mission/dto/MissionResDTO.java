package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    // 미션 도전 성공 응답
    @Builder
    public record ChallengeDTO(
            Long userMissionId,
            LocalDate deadline
    ) {}

    // 내 미션 목록 (진행중/성공)
    @Builder
    public record MyMissionDTO(
            Long userMissionId,
            Long missionId,
            String missionStatus,    // "CHALLENGING" 또는 "SUCCESS"
            int point,
            String storeName,
            LocalDate deadline
    ) {}

    // 미션 단일 정보 (필요하면 사용)
    @Builder
    public record MissionDTO(
            Long missionId,
            int point,
            boolean status
    ) {}

    // 미션 성공 처리 응답
    @Builder
    public record SuccessDTO(
            Long userMissionId,
            String missionStatus    // 항상 "SUCCESS"
    ) {}
}
