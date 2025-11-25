package com.example.week4.service;

import com.example.week4.domain.Review;
import com.example.week4.domain.Store;
import com.example.week4.domain.User;
import com.example.week4.repository.ReviewRepository;
import com.example.week4.repository.StoreRepository;
import com.example.week4.repository.UserRepository;
import com.example.week4.web.dto.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review createReview(Long storeId, ReviewRequestDTO.CreateReviewDto request) {
        
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(request.getContent())
                .star(request.getStar())
                .build();
        
        return reviewRepository.save(review);
    }
}
