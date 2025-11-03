package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 홈 화면에서 지역별 미참여 미션을 조회할 때 사용되는 DTO
 * MissionRepository의 JPQL DTO Projection에서 사용됨.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HomeMissionDto {

    private String region;   // 사용자가 선택한 지역명
    private Long missionId;  // 미션 ID
    private String storeName; // 가게 이름
    private int point;       // 미션 포인트
}
