package com.example.umc9th.domain.review.dto;

import java.time.LocalDate;

public class ReviewReqDTO {

    public record CreateDTO(
            Long memberId,
            Long storeId,
            Float star,
            String content,
            String imageUrl
    ) {}
}
