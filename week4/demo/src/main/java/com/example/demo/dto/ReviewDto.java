package com.example.demo.dto;

import java.math.BigDecimal;

public record ReviewDto(
        Long reviewId,
        String marketName,
        String content,
        BigDecimal star
) {

}
