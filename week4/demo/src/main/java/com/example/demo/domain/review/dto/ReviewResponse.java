package com.example.demo.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;
/*
* 나는 이 친구로 이미지까지 뿌려줄 가장 디테일한 dto를 정의
* */
public record ReviewResponse(
        Long reviewId,
        Long userId,
        Long marketId,
        String marketName,
        String content,
        BigDecimal star,
        List<String> imageUrls
) {}