package com.example.umc9th.domain.user.dto.UserResDTO;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
            Long userId,
            LocalDateTime createAt
    ){}

    // 로그인
    @Builder
    public record LoginDTO(
            Long userId,
            String accessToken
    ){}
}