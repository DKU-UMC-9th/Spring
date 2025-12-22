package com.example.umc_spring_first.domain.mission.dto;

import java.time.LocalDateTime;
public record HomeMissionRowDto(Integer point, String description,
                                LocalDateTime deadline, String name, String address) { }
