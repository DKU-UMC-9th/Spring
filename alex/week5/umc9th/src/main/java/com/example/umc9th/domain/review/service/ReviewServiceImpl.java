package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.review.dto.ReviewRequestCreate;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ReviewResponse createReview(ReviewRequestCreate request) {
        
        User user = userRepository.findById(request.userId())
                                  .orElseThrow(() -> new GeneralException(GeneralErrorCode.INVALID_DATA));

        Store store = storeRepository.findById(request.storeId())
                                     .orElseThrow(() -> new GeneralException(GeneralErrorCode.INVALID_DATA));

        Review review = Review.builder()
                              .user(user)
                              .store(store)
                              .star(request.star())
                              .content(request.content())
                              .dbStatus(true)
                              .build();

        Review saved  = reviewRepository.save(review);

        return toResponse(review);
    }

    @Override
    public List<ReviewResponse> search(Long storeId) {
        List<Review> reviews = reviewRepository.findByStoreId(storeId);

        return reviews.stream()
                .map(this::toResponse)
                .toList();
    }

    private ReviewResponse toResponse(Review review) {

        return new ReviewResponse(
            review.getId(),
            review.getUser().getId(),
            review.getStore().getId(),
            review.getStore().getName(),
            review.getContent(),
            review.getStar()
        );
    }


    // public String queryTest(String name) {
    //     QReview review = QReview.review;
    //     BooleanBuilder builder = new BooleanBuilder();

    //     if (name != null) {
    //         builder.and(review.user.name.eq(name));
    //     }

    //     List<Review> reviewList = reviewRepository.searchReview(builder);
    //     return reviewList.toString();
    // }

    // public List<Review> searchReview(String query, String type) {
    //     QReview review = QReview.review;
    //     BooleanBuilder builder = new BooleanBuilder();

    //     if ("location".equals(type)) {
    //         builder.and(review.store.addr.contains(query));
    //     } else if ("star".equals(type)) {
    //         builder.and(review.star.goe(Integer.parseInt(query)));
    //     } else if ("both".equals(type)) {
    //         String[] parts = query.split("&");
    //         builder.and(review.store.addr.contains(parts[0]));
    //         builder.and(review.star.goe(Integer.parseInt(parts[1])));
    //     }

    //     return reviewRepository.searchReview(builder);
    // }

    // public List<Review> getMyReviews(long userId, String storeName, Integer star) {
    //     QReview review = QReview.review;
    //     BooleanBuilder builder = new BooleanBuilder();

    //     builder.and(review.user.id.eq(userId));


    //     if (storeName != null && !storeName.isEmpty()) {
    //         builder.and(review.store.name.containsIgnoreCase(storeName));
    //     }

    //     if (star != null) {
    //         builder.and(review.star.goe(star));
    //     }

    //     return reviewRepository.searchReview(builder);
    // }
}
