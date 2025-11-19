package com.example.demo.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
/*
* 나는 이 친구로 받을 데이터를 정의
*/
public record ReviewCreateRequest(
        @NotNull @Min(0)
        Long userId,
        @NotNull @Min(0)
        Long marketId,
        @NotBlank
        String content,
        BigDecimal star,
        List<String> imageUrls // optional
) {}
