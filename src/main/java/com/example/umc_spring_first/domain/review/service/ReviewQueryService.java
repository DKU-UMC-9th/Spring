package com.example.umc_spring_first.domain.review.service;

import com.example.umc_spring_first.domain.review.converter.ReviewConverter;
import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.entity.Review;
import com.example.umc_spring_first.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.ReviewPreviewListDTO getReviews(Long storeId, Integer starBand, int page) {

        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Review> entityPage = reviewRepository.searchReviews(storeId, starBand, pageable);

        return ReviewConverter.toPreviewListDTO(entityPage);
    }

    public ReviewResDTO.ReviewPreviewListDTO getMyReviews(Long userId, int page) {

        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Review> entityPage = reviewRepository.searchMyReviews(userId, pageable);

        return ReviewConverter.toPreviewListDTO(entityPage);
    }
}
