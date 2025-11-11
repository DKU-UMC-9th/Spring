package com.example.umc9th.domain.review;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.example.umc9th.domain.review.entity.QReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testSearchReview_ByStoreName() {
        // given
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.store.name.containsIgnoreCase("반이학생마라탕마라반"));

        // when
        List<Review> results = reviewRepository.searchReview(builder);

        // then
        System.out.println("✅ 검색 결과 개수: " + results.size());
        for (Review r : results) {
            System.out.println("가게명: " + r.getStore().getName() +
                    ", 별점: " + r.getStar() +
                    ", 작성자: " + r.getMember().getName());
        }
    }
}
