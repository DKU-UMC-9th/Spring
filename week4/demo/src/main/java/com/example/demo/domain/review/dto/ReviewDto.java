package com.example.demo.domain.review.dto;

import java.math.BigDecimal;


public record ReviewDto(
        Long reviewId,
        String marketName,
        String content,
        BigDecimal star
) {}
