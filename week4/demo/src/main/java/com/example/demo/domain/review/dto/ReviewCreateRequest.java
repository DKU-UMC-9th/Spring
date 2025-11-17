package com.example.demo.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public record ReviewCreateRequest(
        Long userId,
        Long marketId,
        String content,
        BigDecimal star,
        List<String> imageUrls // optional
) {}
