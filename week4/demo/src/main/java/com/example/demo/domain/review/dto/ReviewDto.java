package com.example.demo.domain.review.dto;

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
