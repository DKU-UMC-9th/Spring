package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewQueryDsl {
    List<Review> findByStoreId(Long storeId);
    Page<Review> findByUserIdAndDbStatusTrue(Long userId, Pageable pageable);

}