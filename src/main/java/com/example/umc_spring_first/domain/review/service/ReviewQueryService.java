package com.example.umc_spring_first.domain.review.service;

import com.example.umc_spring_first.domain.review.converter.ReviewConverter;
import com.example.umc_spring_first.domain.review.dto.res.ReviewResDTO;
import com.example.umc_spring_first.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public ReviewResDTO.ReviewPreviewListDTO getReviews(Long storeId, Integer starBand, int page) {

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());

        var dataPage = reviewRepository.searchReviews(storeId, starBand, pageable);

        // Converter에서 Page -> ReviewPreviewListDTO로 변환
        return ReviewConverter.toReviewPreviewListDTO(dataPage);
    }

    @Transactional(readOnly = true)
    public ReviewResDTO.ReviewPreviewListDTO getMyReviews(Long userId, int page) {

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());

        var dataPage = reviewRepository.searchMyReviews(userId, pageable);

        return ReviewConverter.toReviewPreviewListDTO(dataPage);
    }
}
