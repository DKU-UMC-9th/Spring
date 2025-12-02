package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.entity.QReview;
import com.example.umc_spring_first.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {   // 🔹 이름 중요!!

    private final JPAQueryFactory query;

    // ✅ 전체/필터 리뷰 조회
    @Override
    public Page<ReviewResDTO.ReviewPreviewDTO> searchReviews(
            Long storeId,
            Integer starBand,
            Pageable pageable
    ) {
        QReview r = QReview.review;
        QStore s = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();

        if (storeId != null) {
            builder.and(r.store.id.eq(storeId));
        }

        if (starBand != null) {
            if (starBand == 5) {
                builder.and(r.rating.eq(5.0f));
            } else {
                float low = starBand;
                float high = starBand + 0.9999f;
                builder.and(r.rating.between(low, high));
            }
        }

        List<ReviewResDTO.ReviewPreviewDTO> content = query
                .select(Projections.constructor(
                        ReviewResDTO.ReviewPreviewDTO.class,
                        r.id,
                        s.name,
                        r.rating,
                        r.content,
                        r.createAt   // createdAt: LocalDate 로 받을 거면 이렇게
                ))
                .from(r)
                .join(r.store, s)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(r.id.desc())
                .fetch();

        Long total = query
                .select(r.count())
                .from(r)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    // ✅ 내가 작성한 리뷰만 조회
    @Override
    public Page<ReviewResDTO.ReviewPreviewDTO> searchMyReviews(
            Long userId,
            Pageable pageable
    ) {
        QReview r = QReview.review;
        QStore s = QStore.store;

        List<ReviewResDTO.ReviewPreviewDTO> content = query
                .select(Projections.constructor(
                        ReviewResDTO.ReviewPreviewDTO.class,
                        r.id,
                        s.name,
                        r.rating,
                        r.content,
                        r.createAt
                ))
                .from(r)
                .join(r.store, s)
                .where(r.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(r.id.desc())
                .fetch();

        Long total = query
                .select(r.count())
                .from(r)
                .where(r.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
