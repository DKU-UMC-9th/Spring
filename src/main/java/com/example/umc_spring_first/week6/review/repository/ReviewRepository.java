package com.example.umc_spring_first.week6.review.repository;

import com.example.umc_spring_first.week6.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {
}
