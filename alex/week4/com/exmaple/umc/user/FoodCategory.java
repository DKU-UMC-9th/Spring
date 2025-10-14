package com.example.umc.user;

import com.example.umc.common.BaseTime;
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
