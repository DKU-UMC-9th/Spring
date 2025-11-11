package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    @Modifying
    @Query(
            value = "INSERT INTO review (member_id, store_id, star, content, created_at) " +
                    "VALUES (:memberId, :storeId, :star, :content, NOW())",
            nativeQuery = true
    )
    void insertReview(
            @Param("memberId") Long memberId,
            @Param("storeId") Long storeId,
            @Param("star") int star,
            @Param("content") String content
    );


}
