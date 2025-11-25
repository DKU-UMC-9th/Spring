package com.example.umc9th.domain.member.dto.res;

import java.time.LocalDateTime;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}
}
