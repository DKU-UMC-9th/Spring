package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    /**
     * 사용자의 리뷰 리스트 변환
     * Page<ReviewResponse> → ReviewListDTO
     */
    public static ReviewResDTO.ReviewListDTO toReviewListDTO(Page<ReviewResponse> page) {

        List<ReviewResDTO.ReviewDTO> reviewDTOs = page.getContent().stream()
                .map(r -> ReviewResDTO.ReviewDTO.builder()
                        .reviewId(r.getReviewId())
                        .content(r.getContent())
                        .star(r.getStar())
                        .createdAt(r.getCreatedAt())
                        .build()
                )
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .currentPage(page.getNumber() + 1) // 1-based 페이지
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    /**
     * 단일 리뷰 변환 (ReviewResponse → ReviewDTO)
     */
    private static ReviewResDTO.ReviewDTO toReviewDTO(ReviewResponse review) {
        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }

    /**
     * Review 생성 변환 (CreateReviewDTO → Review Entity)
     */
    public static Review toReview(ReviewReqDTO.CreateReviewDTO dto, User user, Store store) {
        return Review.builder()
                .star(dto.getStar())
                .content(dto.getContent())
                .user(user)
                .store(store)
                .build();
    }

    /**
     * 리뷰 생성 결과 변환 (Review → CreateReviewResultDTO)
     */
    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    /**
     * 가게 리뷰 목록 조회 변환
     * Page<Review> → ReviewPreviewListDTO
     */
    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        return ReviewResDTO.ReviewPreviewListDTO.builder()
                .reviewList(
                        reviewPage.getContent().stream()
                                .map(r -> ReviewResDTO.ReviewPreviewDTO.builder()
                                        .ownerNickname(r.getUser().getName())
                                        .score(r.getStar())
                                        .body(r.getContent())
                                        .createdAt(r.getCreatedAt().toLocalDate())
                                        .build()
                                ).toList()
                )
                .listSize(reviewPage.getSize())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

}
