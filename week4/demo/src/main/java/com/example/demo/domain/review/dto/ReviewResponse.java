package com.example.demo.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public record ReviewResponse(
        Long reviewId,
        Long userId,
        Long marketId,
        String marketName,
        String content,
        BigDecimal star,
        List<String> imageUrls
) {}