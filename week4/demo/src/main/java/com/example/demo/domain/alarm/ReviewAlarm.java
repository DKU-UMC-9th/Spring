package com.example.demo.domain.alarm;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.member.Users;
import com.example.demo.domain.restruant.FoodMarket;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "review_alarm")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReviewAlarm extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Users user;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="foodmarket_id", nullable=false)
    private FoodMarket foodmarket;

    @Column(nullable=false, length=200)
    private String content;
}
