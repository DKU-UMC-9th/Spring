package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.common.BaseTime;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name="review")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review extends BaseTime {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false)
  private User user;

  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="store_id", nullable=false)
  private Store store;

  @Column(nullable=false) private Integer star;
  @Column(nullable=false, length=1000) private String content;
  @Column(nullable=false) private Boolean dbStatus;

  @OneToMany(mappedBy="review", cascade=CascadeType.ALL, orphanRemoval=true)
  private List<ReviewImage> images;

  @OneToOne(mappedBy="review", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
  private ReviewReply reply;
}