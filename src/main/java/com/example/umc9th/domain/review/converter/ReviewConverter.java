package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO.CreateReviewRequest;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import java.time.LocalDate;
import org.springframework.data.domain.Page;

public class ReviewConverter {

    // Entity -> DTO
    public static ReviewResDTO.CreateReviewResponse toCreateReviewRes(Review review){
        return ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreviewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .ownerNickname(review.getMember().getNickname())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    // DTO -> Entity
    public static Review toReview(CreateReviewRequest dto, Member member, Restaurant restaurant){
        return Review.create(member, restaurant, dto.getStar(), dto.getContent());
    }
}
