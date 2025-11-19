package com.example.demo.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/*
* 이친구는 이미지는 안뿌려주는 dto에요.
*/
public record ReviewDto(
        Long reviewId,
        String marketName,
        String content,
        BigDecimal star
) {}
