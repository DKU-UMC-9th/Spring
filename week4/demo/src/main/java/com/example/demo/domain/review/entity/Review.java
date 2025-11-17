package com.example.demo.domain.review.entity;


import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.member.entity.Users;
import com.example.demo.domain.restruant.entity.FoodMarket;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name = "review")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Review extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="market_id", nullable=false)
    private FoodMarket market;

    @Column(nullable=false, length=100)
    private String content;

    @Column(nullable=false, precision=2, scale=1)
    private BigDecimal star;
}

