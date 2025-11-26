package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResDTO.CreateReviewResultDTO createReview(ReviewReqDTO.CreateReviewDTO dto) {
        // 하드코딩된 유저 (DB에 있는 유저 ID 1번)
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 가게 조회
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // Review 엔티티 생성
        Review review = ReviewConverter.toReview(dto, user, store);

        // DB 저장
        Review savedReview = reviewRepository.save(review);

        // 응답 DTO 반환
        return ReviewConverter.toCreateReviewResultDTO(savedReview);
    }
}