package com.example.umc9th.domain.review.service.query;

import static com.example.umc9th.domain.restaurant.entity.QRegion.region;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.domain.restaurant.exception.RestaurantException;
import com.example.umc9th.domain.restaurant.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO.MyReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Review> searchReview(String query, String type){
        // Q클래스 정의
        QReview review = QReview.review;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        if(type.equals("region")){
            builder.and(region.name.contains(query));
        }
        if(type.equals("star")){
            builder.and(review.star.goe(Float.parseFloat(query)));
        }
        if(type.equals("both")){

            // & 기준 변환
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            // 동적 쿼리
            builder.and(region.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        // 리턴
        return reviewList;
    }

    @Override
    // 내가 작성한 리뷰
    public List<MyReviewResponse> searchMyReviews(Long memberId, String restaurantName, Integer star){
        // Q 클래스 선언
        QReview review = QReview.review;
        //QRestaurant restaurant = QRestaurant.restaurant;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용
        builder.and(review.member.id.eq(memberId)); //내 리뷰만

        if(restaurantName != null && !restaurantName.isEmpty()){
            builder.and(review.restaurant.name.contains(restaurantName));
        }
        if(star != null){
            float minStar = star.floatValue();
            float maxStar = star + 1.0f;
            builder.and(review.star.goe(minStar));
            builder.and(review.star.lt(maxStar));
        }

        // Repository 사용 & 결과 매핑
        List<MyReviewResponse> myReviews = reviewRepository.searchMyReviews(builder);

        // 리턴
        return myReviews;

    }

    // 가게의 리뷰 목록 조회
    @Override
    public ReviewResDTO.ReviewPreviewListDTO findReview(
            String restaurantName,
            Integer page
    ){
        // 가게 존재 여부 검증
        Restaurant restaurant = restaurantRepository.findByName(restaurantName)
                .orElseThrow(() -> new RestaurantException(RestaurantErrorCode.NOT_FOUND));

        // 가게에 맞는 리뷰 가져오기(Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByRestaurant(restaurant, pageRequest);

        // 결과 응답 DTO 변환(컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    // 내가 작성한 리뷰 목록
    @Override
    public ReviewResDTO.ReviewPreviewListDTO findMyReview(
            Long memberId,
            Integer page
    ){
        // 유저 존재 여부 검증
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 내가 작성한 리뷰 가져오기(Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        // 결과 응답 DTO 변환
        return ReviewConverter.toReviewPreviewListDTO(result);

    }

}
