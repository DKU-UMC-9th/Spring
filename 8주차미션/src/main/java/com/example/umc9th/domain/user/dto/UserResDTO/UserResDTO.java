package com.example.umc9th.domain.user.dto.UserResDTO;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {
    @Builder
    public record JoinDTO(
            Long userId,           // memberId -> userId로 변경
            LocalDateTime createAt
    ){}
}