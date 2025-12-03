package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findReviewsByMarket_IdOrderByUpdatedAtDesc(Long marketId);
    @Query("SELECT r from Review r join fetch r.user where r.market.id=:marketId order by r.updatedAt desc ")
    List<Review> findReviewsByMarket_IdOrderByUpdatedAtDesc2(@Param("marketId") Long marketId);
}
