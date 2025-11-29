package com.example.umc9th.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    // 미션 도전하기 요청
    public record ChallengeDTO(
            Long memberId,
            Long missionId,
            LocalDate deadline   // null이면 서비스에서 기본값(예: +7일) 설정
    ) {}

    // 미션 성공 처리 요청
    public record SuccessDTO(
            Long memberId,
            Long userMissionId   // 도전한 user_mission PK
    ) {}
}
