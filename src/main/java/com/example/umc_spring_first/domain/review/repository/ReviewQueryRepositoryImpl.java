package com.example.umc_spring_first.domain.review.repository;

import com.example.umc_spring_first.domain.review.dto.MyReviewRowDto;
import com.example.umc_spring_first.domain.review.entity.QReview;
import com.example.umc_spring_first.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<MyReviewRowDto> findReviews(Long storeId, Integer starBand, Pageable pageable) {
        QReview r = QReview.review;
        QStore s = QStore.store;

        BooleanBuilder where = new BooleanBuilder();

        //store 필터는 r.store.id 로 통일
        if (storeId != null) {
            where.and(r.store.id.eq(storeId));
        }

        // 별점 밴드(정수별 1점 구간: 4 → 4.0~4.99), 5는 5.0 고정
        if (starBand != null) {
            if (starBand == 5) {
                where.and(r.rating.goe(5.0f).and(r.rating.loe(5.0f)));
            } else if (starBand >= 1 && starBand <= 4) {
                float lower = starBand;
                float upper = starBand + 0.9999f;
                where.and(r.rating.goe(lower).and(r.rating.loe(upper)));
            }
        }

        List<MyReviewRowDto> content = query
                .select(Projections.constructor(MyReviewRowDto.class,
                        r.id,
                        r.store.id,
                        s.name,
                        r.rating,
                        r.content,
                        r.createAt
                ))
                .from(r)
                .join(r.store, s)
                .where(where)
                .orderBy(r.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //  where 에 s 필드 참조가 없도록 구성했으므로 join 없이 count 가능
        var countQuery = query
                .select(r.id.count())
                .from(r)
                .where(where);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
