package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;

public class ReviewConverter {

    public static ReviewResDTO.MyReviewResponse toMyReviewDTO(
            Long id, String content, float star, String reply
    ){
        return ReviewResDTO.MyReviewResponse.builder()
                .id(id)
                .content(content)
                .star(star)
                .reply(reply)
                .build();
    }
}
