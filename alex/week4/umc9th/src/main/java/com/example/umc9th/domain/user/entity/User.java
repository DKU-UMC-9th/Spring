package com.example.umc9th.domain.user.entity;

import com.example.umc9th.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "`User`")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User extends BaseTime {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, length=50) private String name;
  @Column(nullable=false) private Boolean sex;
  @Column(nullable=false) private LocalDate birth;
  @Column(nullable=false, length=255) private String addr;

  @Column(nullable=false) private Boolean userType;
  @Column(nullable=false, length=255) private String socialUID;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false, length=10) private SocialType socialType;

  @Column(unique=true, length=100) private String email;
  @Column(unique=true, length=20)  private String phoneNum;

  @Column(nullable=false) private Long point;
  @Column(nullable=false) private Boolean dbStatus;

  @ManyToMany
  @JoinTable(
      name = "user_interest",
      joinColumns = @JoinColumn(name="user_id"),
      inverseJoinColumns = @JoinColumn(name="food_cat_id")
  )
  private Set<FoodCategory> interests;
}
