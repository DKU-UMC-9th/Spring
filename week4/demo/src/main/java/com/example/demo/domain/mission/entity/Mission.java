package com.example.demo.domain.mission.entity;

import com.example.demo.global.entity.BaseEntity;
import com.example.demo.domain.restruant.entity.FoodMarket;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "mission")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Mission extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="market_id", nullable=false)
    private FoodMarket market;

    @Column(nullable=false, length=100)
    private String content;

    @Column(name="mission_point", nullable=false)
    private Integer missionPoint;
}