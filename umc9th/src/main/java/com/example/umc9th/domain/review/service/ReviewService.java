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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public String queryTest(String name) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        if (name != null) {
            builder.and(review.member.name.eq(name));
        }

        List<Review> reviewList = reviewRepository.searchReview(builder);
        return reviewList.toString();
    }

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

    //미션
    public List<Review> getMyReviews(long memberId, String storeName, Integer star) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 로그인 사용자 조건
        builder.and(review.member.id.eq(memberId));

        // 가게명 필터
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        // 별점 필터
        if (star != null) {
            builder.and(review.star.goe(star));
        }

        return reviewRepository.searchReview(builder);
    }

    //리뷰 생성
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

