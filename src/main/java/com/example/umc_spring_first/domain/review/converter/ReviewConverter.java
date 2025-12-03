package com.example.umc_spring_first.domain.review.converter;

import com.example.umc_spring_first.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.entity.Review;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.user.entity.User;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

public class ReviewConverter {

    // DTO → Entity
    public static Review toReview(ReviewReqDTO.CreateReviewRequest dto, User user, Store store) {
        LocalDateTime now = LocalDateTime.now();
        return Review.builder()
                .user(user)
                .store(store)
                .content(dto.content())
                .rating(dto.rating())
                .createAt(now)
                .updateAt(now)
                .build();
    }

    // Entity → PreviewDTO
    public static ReviewResDTO.ReviewPreviewDTO toPreviewDTO(Review r) {
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .reviewId(r.getId())
                .storeName(r.getStore().getName())
                .rating(r.getRating())
                .content(r.getContent())
                .createdAt(r.getCreateAt())
                .build();
    }

    // Page<Entity> → PageDTO
    public static ReviewResDTO.ReviewPreviewListDTO toPreviewListDTO(Page<Review> page) {
        return ReviewResDTO.ReviewPreviewListDTO.builder()
                .reviewList(
                        page.getContent().stream()
                                .map(ReviewConverter::toPreviewDTO)
                                .toList()
                )
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // Entity → CreateResponseDTO
    public static ReviewResDTO.CreateReviewResponse toCreateResponse(Review review) {
        return ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreateAt())
                .build();
    }
}

