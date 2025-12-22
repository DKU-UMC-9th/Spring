package com.example.week4.web.dto;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewDto {
        private Long userId;
        private String content;
        private Integer star;
    }
}