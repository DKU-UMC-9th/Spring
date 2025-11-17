package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
    List<ReviewImage> findByReview(Review review);
    void deleteByReview(Review review);
}
