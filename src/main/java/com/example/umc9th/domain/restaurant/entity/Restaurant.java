package com.example.umc9th.domain.restaurant.entity;

import com.example.umc9th.domain.restaurant.enums.RestaurantType;
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
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; //가게명

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RestaurantType restaurantType = RestaurantType.ETC; //가게 유형

    @Column(name = "address", nullable = false)
    private String address; //주소

    @Column(name = "is_open", nullable = false)
    private boolean open; //영업 여부

    @Column(name = "star", nullable = false)
    private float star; //별점

    @Column(name = "latitude", nullable = false)
    private Double latitude; //위도

    @Column(name = "longitude", nullable = false)
    private Double longitude; //경도

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}
