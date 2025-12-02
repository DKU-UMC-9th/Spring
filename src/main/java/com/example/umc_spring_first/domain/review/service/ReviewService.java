package com.example.umc_spring_first.domain.review.service;

import com.example.umc_spring_first.domain.review.converter.ReviewConverter;
import com.example.umc_spring_first.domain.review.dto.req.ReviewReqDTO;
import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.entity.Review;
import com.example.umc_spring_first.domain.review.repository.ReviewRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewResponse createReview(ReviewReqDTO.CreateReviewRequest req) {

        Long userId = 1L; // 로그인 미구현 → 하드코딩

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Store store = storeRepository.findById(req.storeId())
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        // DTO -> Entity 변환은 Converter에 위임
        Review review = ReviewConverter.toReview(req, user, store);

        reviewRepository.save(review);

        // Entity -> 응답 DTO도 Converter에 위임
        return ReviewConverter.toCreateReviewRes(review);
    }
}
