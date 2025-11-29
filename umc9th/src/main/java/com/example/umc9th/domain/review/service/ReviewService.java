package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // QueryDSL 검색 테스트
    public List<Review> searchReview(String query, String type) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if ("location".equals(type)) {
            builder.and(review.store.address.contains(query));
        } else if ("star".equals(type)) {
            builder.and(review.star.goe(Integer.parseInt(query)));
        } else if ("both".equals(type)) {
            String[] parts = query.split("&");
            builder.and(review.store.address.contains(parts[0]));
            builder.and(review.star.goe(Integer.parseInt(parts[1])));
        }

        return reviewRepository.searchReview(builder);
    }

    // 9주차 — 내가 작성한 리뷰 조회 (페이징)
    public ReviewResDTO.MyReviewPageDTO getMyReviews(
            long memberId,
            String storeName,
            Integer star,
            Pageable pageable
    ) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isBlank()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        if (star != null) {
            builder.and(review.star.goe(star));
        }

        Page<Review> reviewPage =
                reviewRepository.searchReviewWithPaging(builder, pageable);

        return ReviewConverter.toMyReviewPageDTO(reviewPage);
    }

    // 리뷰 생성
    @Transactional
    public ReviewResDTO.CreateDTO createReview(ReviewReqDTO.CreateDTO dto) {

        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toEntity(dto, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toCreateDTO(review);
    }
}
