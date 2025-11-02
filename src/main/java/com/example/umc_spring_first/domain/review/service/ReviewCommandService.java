package com.example.umc_spring_first.domain.review.service;

import com.example.umc_spring_first.domain.review.entity.Review;
import com.example.umc_spring_first.domain.review.repository.ReviewRepository;
import com.example.umc_spring_first.domain.store.entity.Store;
import com.example.umc_spring_first.domain.user.entity.User;
import com.example.umc_spring_first.domain.store.repository.StoreRepository;
import com.example.umc_spring_first.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Long writeReview(Long userId, Long storeId, String content, Float rating, String image) {
        User user = userRepository.getReferenceById(userId);
        Store store = storeRepository.getReferenceById(storeId);
        Review review = Review.builder()
                .user(user).store(store)
                .content(content).rating(rating).image(image)
                .build();
        return reviewRepository.save(review).getId();
    }
}
