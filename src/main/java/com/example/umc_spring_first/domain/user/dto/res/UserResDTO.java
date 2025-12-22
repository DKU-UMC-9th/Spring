package com.example.umc_spring_first.domain.user.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
            Long userId,
            LocalDateTime createAt
    ){}

    @Builder
    public record LoginDTO(
            Long userId,
            String accessToken
    ){}
}