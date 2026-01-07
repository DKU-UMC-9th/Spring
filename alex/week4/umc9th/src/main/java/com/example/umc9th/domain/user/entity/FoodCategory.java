package com.example.umc9th.domain.user.entity;

import com.example.umc9th.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="food_category")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodCategory extends BaseTime {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, length=50, unique=true)
  private String name;
}
