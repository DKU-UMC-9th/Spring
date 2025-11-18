package com.example.demo.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;
/*
* 나는 이 친구로 받을 데이터를 정의
*/
public record ReviewCreateRequest(
        Long userId,
        Long marketId,
        String content,
        BigDecimal star,
        List<String> imageUrls // optional
) {}
