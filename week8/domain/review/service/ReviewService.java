package com.example.umc_spring_first.domain.review.service;

import com.example.umc_spring_first.domain.review.dto.ReviewCreateRequest;
import com.example.umc_spring_first.domain.review.entity.Review;
import com.example.umc_spring_first.domain.review.repository.ReviewRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public Long createReview(ReviewCreateRequest req) {

        // 로그인 미구현 → 유저 하드코딩
        Long userId = 1L;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        LocalDateTime now = LocalDateTime.now();

        Review review = Review.builder()
                .user(user)
                .store(store)
                .rating(req.getRating())
                .content(req.getContent())
                // .image(req.getImage())  // 필요하면 DTO에 추가해서 사용
                .createAt(now)
                .updateAt(now)
                .build();

        reviewRepository.save(review);
        return review.getId();
    }
}
