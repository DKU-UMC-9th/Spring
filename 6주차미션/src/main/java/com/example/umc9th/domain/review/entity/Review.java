package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private Float star;

    @Column(columnDefinition = "TEXT")
    private String content;

    // User와의 연관 관계 (N:1) - 연관 관계의 주인
    // Review 입장에서 User는 N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Store와의 연관 관계 (N:1) - 연관 관계의 주인
    // Review 입장에서 Store는 N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // ReviewPhoto와의 연관 관계 (1:N)
    // Review 입장에서 ReviewPhoto는 1:N
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    // ReviewReply와의 연관 관계 (1:N)
    // Review 입장에서 ReviewReply는 1:N // 헷갈림
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ReviewReply> reviewReplyList = new ArrayList<>();
}