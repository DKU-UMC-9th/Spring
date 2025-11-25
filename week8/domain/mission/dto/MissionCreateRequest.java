package com.example.umc_spring_first.domain.mission.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionCreateRequest {
    private String description;
    private int point;
}
