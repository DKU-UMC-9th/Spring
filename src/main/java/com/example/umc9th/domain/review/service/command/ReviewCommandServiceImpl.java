package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.exception.RestaurantException;
import com.example.umc9th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO.CreateReviewRequest;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.CreateReviewResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 작성하기
    @Override
    @Transactional
    public CreateReviewResponse createReview(Long restaurantId, CreateReviewRequest request){

        // 유저 id 하드코딩
        Long memberId = 1L;
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->new MemberException(MemberErrorCode.NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        // 정적 팩토리 메서드 사용
        Review review = ReviewConverter.toReview(request, member, restaurant);

        // 리뷰 저장
        reviewRepository.save(review);

        return ReviewConverter.toCreateReviewRes(review);

    }
}
