package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
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
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", nullable = false, length = 50)
    private String name;

    @Column(name = "detailAddress", length = 100)
    private String detailAddress;

    // Location과의 연관 관계 (N:1) - 연관 관계의 주인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    // Review와의 연관 관계 (1:N)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    // Mission과의 연관 관계 (1:N)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

}