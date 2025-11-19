package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Users;
import com.example.demo.domain.member.repository.UsersRepository;
import com.example.demo.domain.restruant.entity.FoodMarket;
import com.example.demo.domain.restruant.entity.FoodMarketRepository;
import com.example.demo.domain.review.dto.ReviewCreateRequest;
import com.example.demo.domain.review.dto.ReviewDto;
import com.example.demo.domain.review.dto.ReviewResponse;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.entity.ReviewImage;
import com.example.demo.domain.review.repository.ReviewImageRepository;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.global.apiPayload.Exception.ReviewException;
import com.example.demo.global.apiPayload.response.ErrorCode;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.List;

import static com.example.demo.domain.restruant.entity.QFoodMarket.foodMarket;
import static com.example.demo.domain.review.entity.QReview.review;


@Service
@AllArgsConstructor
public class ReviewService  {
    private final JPAQueryFactory qf;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final UsersRepository usersRepository;
    private final FoodMarketRepository foodMarketRepository;

    @Transactional
    public ReviewResponse createReview(ReviewCreateRequest request){
        Users user=usersRepository.findById(request.userId())
                .orElseThrow(()->new ReviewException(ErrorCode.REVIEW_CREATE_ERROR));
        FoodMarket market = foodMarketRepository.findById(request.marketId())
                .orElseThrow(()->new ReviewException(ErrorCode.REVIEW_CREATE_ERROR));
        Review reviewEntity = new Review();
        reviewEntity.setUser(user);
        reviewEntity.setMarket(market);
        reviewEntity.setContent(request.content());
        reviewEntity.setStar(request.star());

        Review saved = reviewRepository.save(reviewEntity);

        if(request.imageUrls()!=null&& !request.imageUrls().isEmpty()){
            for(String url:request.imageUrls()){
                ReviewImage reviewImage=new ReviewImage();
                reviewImage.setReview(saved);
                reviewImage.setUrl(url);
                reviewImageRepository.save(reviewImage);
            }
        }

        return toResponse(saved);
    }

    @Transactional
    public ReviewResponse updateReview(Long reviewId, Long userId,ReviewCreateRequest request) {

        Review reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(()->new ReviewException(ErrorCode.REVIEW_UPDATE_ERROR));

        if(!reviewEntity.getUser().getId().equals(userId)){
           new ReviewException(ErrorCode.REVIEW_UPDATE_ERROR);
        }

        reviewEntity.setContent(request.content());
        reviewEntity.setStar(request.star());

        reviewImageRepository.deleteByReview(reviewEntity);

        if(request.imageUrls()!=null&& !request.imageUrls().isEmpty()){
            for(String url:request.imageUrls()){
                ReviewImage reviewImage=new ReviewImage();
                reviewImage.setReview(reviewEntity);
                reviewImage.setUrl(url);
                reviewImageRepository.save(reviewImage);
            }
        }
        return toResponse(reviewEntity);
    }
    public void deleteReview(Long reviewId,Long userId) {

        Review reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(()->new ReviewException(ErrorCode.REVIEW_DELETE_ERROR));

        if(!reviewEntity.getUser().getId().equals(userId)){
            throw new ReviewException(ErrorCode.REVIEW_DELETE_ERROR);
        }
        reviewRepository.delete(reviewEntity);
        reviewImageRepository.deleteByReview(reviewEntity);
    }

    public List<ReviewDto> findMyReviews(Long userId, String marketName, Integer starBand) {
        // userId should be required; validate if needed
        return qf.select(Projections.constructor(ReviewDto.class,
                        review.id,
                        review.market.name,
                        review.content,
                        review.star))
                .from(review)
                .join(review.market, foodMarket)
                .where(
                        review.user.id.eq(userId),
                        marketNameEquals(marketName),
                        starBandPredicate(starBand)
                )
                .orderBy(review.id.desc())
                .fetch();
    }

    public List<ReviewResponse> findReviewsByMarket(Long marketId, Integer starBand) {
        List<Review> reviews = qf.selectFrom(review)
                .leftJoin(review.market).fetchJoin()
                .leftJoin(review.user).fetchJoin()
                .where(
                        review.market.id.eq(marketId),
                        starBandPredicate(starBand)
                )
                .orderBy(review.id.desc())
                .fetch();

        return reviews.stream()
                .map(this::toResponse)
                .toList();
    }

    private BooleanExpression starBandPredicate(Integer band) {
        if (band == null) return null;
        if (band == 5) return review.star.eq(new BigDecimal("5.0"));
        if (band >= 0 && band <= 4) {
            BigDecimal min = new BigDecimal(band + ".0");
            BigDecimal max = new BigDecimal((band + 1) + ".0");
            return review.star.goe(min).and(review.star.lt(max));
        }
        return null;
    }
    private BooleanExpression marketNameEquals(String marketName) {
        return (marketName == null || marketName.isBlank())
                ? null
                : review.market.name.eq(marketName);
    }
    private ReviewResponse toResponse(Review reviewEntity) {
        List<String> urls = reviewEntity.getId() == null
                ? Collections.emptyList()
                : reviewImageRepository.findByReview(reviewEntity)
                .stream()
                .map(ReviewImage::getUrl)
                .toList();

        return new ReviewResponse(
                reviewEntity.getId(),
                reviewEntity.getUser().getId(),
                reviewEntity.getMarket().getId(),
                reviewEntity.getMarket().getName(),
                reviewEntity.getContent(),
                reviewEntity.getStar(),
                urls
        );
    }
}

