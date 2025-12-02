package com.example.umc_spring_first.domain.review.converter;

import com.example.umc_spring_first.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.entity.Review;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewConverter {

    // ✅ DTO -> Entity (리뷰 생성용)
    public static Review toReview(
            ReviewReqDTO.CreateReviewRequest dto,
            User user,
            Store store
    ) {
        LocalDateTime now = LocalDateTime.now();

        return Review.builder()
                .user(user)
                .store(store)
                .rating(dto.rating())
                .content(dto.content())
                .createAt(now)
                .updateAt(now)
                .build();
    }

    // ✅ Entity -> 생성 응답 DTO
    public static ReviewResDTO.CreateReviewResponse toCreateReviewRes(Review review) {
        return ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreateAt())
                .build();
    }

    // ✅ Page<ReviewPreviewDTO> -> ReviewPreviewListDTO (페이징 래핑)
    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(
            Page<ReviewResDTO.ReviewPreviewDTO> page
    ) {
        return ReviewResDTO.ReviewPreviewListDTO.builder()
                // Converter에서 for문 쓰지 말라고 해서, stream으로 한 번 감싸 줌
                .reviewList(page.getContent().stream().toList())
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // (참고용) 만약 엔티티에서 직접 리스트를 만들고 싶을 때 쓸 수 있는 버전
    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(LocalDate.from(review.getCreateAt()))
                .build();
    }
}
