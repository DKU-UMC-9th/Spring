package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<ReviewResponse> getReviewsByUserWithFilters(
            Long userId,
            Long storeId,
            Integer minStar,
            Integer maxStar,
            Integer page
    ) {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QReview.review.user.id.eq(userId));

        if (storeId != null) builder.and(QReview.review.store.id.eq(storeId));
        if (minStar != null) builder.and(QReview.review.star.goe(minStar));
        if (maxStar != null) builder.and(QReview.review.star.loe(maxStar));

        PageRequest pageable = PageRequest.of(page - 1, 10);

        return reviewRepository.searchReview(builder, pageable);
    }

    /**
     * 가게 리뷰 조회
     */

    @Override
    public ReviewResDTO.ReviewPreviewListDTO findReview(String storeName, Integer page) {

        Pageable pageable = PageRequest.of(page, 10);

        // 1) Store 조회
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // 2) Store의 리뷰 조회
        Page<Review> reviewPage = reviewRepository.findByStore(store, pageable);

        // 3) Converter 사용해 DTO로 변환
        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }
}
