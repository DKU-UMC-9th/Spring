package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

import java.time.LocalDate;

public class ReviewConverter {

    public static Review toEntity(ReviewReqDTO.CreateDTO dto, Member member, Store store) {
        return Review.builder()
                .star(dto.star())
                .content(dto.content())
                .imageUrl(dto.imageUrl())
                .member(member)
                .store(store)
                .status(true)
                .build();
    }

    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
