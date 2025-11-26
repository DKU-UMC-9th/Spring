package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)  // 조회 작업이므로 readOnly
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponse> getReviewsByUserWithFilters(
            Long userId,
            Long storeId,
            Integer minStar,
            Integer maxStar
    ) {
        // 검증: 필수 파라미터 체크
        validateUserId(userId);

        // 검증: 별점 범위 체크
        validateStarRange(minStar, maxStar);

        // Q 클래스 선언
        QReview review = QReview.review;

        // BooleanBuilder로 동적 조건 생성
        BooleanBuilder builder = new BooleanBuilder();

        // 필수 조건: 특정 사용자의 리뷰만
        builder.and(review.user.id.eq(userId));

        // 선택 조건: 가게 필터링
        if (storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        // 선택 조건: 별점 필터링
        if (minStar != null && maxStar != null) {
            builder.and(review.star.between(minStar.floatValue(), maxStar.floatValue()));
        } else if (minStar != null) {
            builder.and(review.star.goe(minStar.floatValue()));
        } else if (maxStar != null) {
            builder.and(review.star.loe(maxStar.floatValue()));
        }

        // Repository 호출 & 결과 반환
        return reviewRepository.searchReview(builder);
    }

    /**
     * 사용자 ID 검증
     */
    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new ReviewException(ReviewErrorCode.INVALID_USER_ID);
        }
    }

    /**
     * 별점 범위 검증
     */
    private void validateStarRange(Integer minStar, Integer maxStar) {
        if (minStar != null && (minStar < 1 || minStar > 5)) {
            throw new ReviewException(ReviewErrorCode.INVALID_STAR_RANGE);
        }

        if (maxStar != null && (maxStar < 1 || maxStar > 5)) {
            throw new ReviewException(ReviewErrorCode.INVALID_STAR_RANGE);
        }

        if (minStar != null && maxStar != null && minStar > maxStar) {
            throw new ReviewException(ReviewErrorCode.INVALID_STAR_RANGE);
        }
    }
}
