package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static Review toEntity(ReviewReqDTO.CreateDTO dto, Member member, Store store) {
        return Review.builder()
                .star(dto.star())
                .content(dto.content())
                .member(member)
                .store(store)
                .status(true)
                .build();
    }

    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResDTO.MyReviewPageDTO toMyReviewPageDTO(Page<Review> reviewPage) {

        List<ReviewResDTO.MyReviewDTO> list = reviewPage.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        return ReviewResDTO.MyReviewPageDTO.builder()
                .contents(list)
                .page(reviewPage.getNumber() + 1)
                .size(reviewPage.getSize())
                .totalElements(reviewPage.getTotalElements())
                .totalPages(reviewPage.getTotalPages())
                .build();
    }
}
