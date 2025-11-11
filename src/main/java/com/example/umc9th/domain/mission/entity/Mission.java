package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.restaurant.entity.Restaurant;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content; //미션 내용

    @Column(name = "verification_code", nullable = false)
    private Long verificationCode; //사장님 구분 번호

    @Column(name = "point", nullable = false)
    private int point; //포인트

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt; //만료 일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
