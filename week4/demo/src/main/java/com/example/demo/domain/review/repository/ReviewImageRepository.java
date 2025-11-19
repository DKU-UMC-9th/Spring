package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
    List<ReviewImage> findByReview(Review review);
    void deleteByReview(Review review);
}
