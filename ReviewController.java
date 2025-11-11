package com.koo.week6.controller; 

import com.koo.week6.entity.Review; 
import com.koo.week6.dto.ReviewResponseDto; 
import com.koo.week6.repository.ReviewQueryRepository; 
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewQueryRepository reviewQueryRepository; 

    @GetMapping("/{storeId}/reviews")
    public ResponseEntity<Page<ReviewResponseDto>> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(required = false) List<Integer> starRating, 
            Pageable pageable
    ) {
        // 1. 리포지토리에서 조건에 맞는 Review 엔티티 목록 조회
        Page<Review> reviewPage = reviewQueryRepository.findReviewsByStoreAndFilter(storeId, starRating, pageable);
        
        // 2. Review 엔티티 Page를 ReviewResponseDto Page로 변환
        Page<ReviewResponseDto> response = reviewPage.map(ReviewResponseDto::from); 

        return ResponseEntity.ok(response);
    }
}
