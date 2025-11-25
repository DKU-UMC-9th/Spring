package com.example.umc_spring_first.domain.mission.dto;

import com.example.umc_spring_first.domain.mission.enums.UserMissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyMissionRowDto {
    private Integer point;
    private String storeName;
    private String description;
    private UserMissionStatus status;  // String → Enum(UserMissionStatus)로 변경
}
